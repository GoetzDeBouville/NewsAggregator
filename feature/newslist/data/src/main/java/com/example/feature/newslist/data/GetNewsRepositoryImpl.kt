package com.example.feature.newslist.data

import android.util.Log
import com.example.core.data.db.dao.NewsItemDao
import com.example.core.data.db.mapToEntity
import com.example.core.data.db.toDomain
import com.example.core.data.models.ItemDto
import com.example.core.data.models.mappers.toDomain
import com.example.core.data.network.api.NetworkClient
import com.example.core.data.network.models.Response
import com.example.core.data.network.models.StatusCode
import com.example.core.data.network.models.mapToErrorType
import com.example.core.data.network.models.request.NewsItemsRequest
import com.example.core.data.network.models.response.NewsItemsResponse
import com.example.core.domain.models.ErrorType
import com.example.core.domain.models.Item
import com.example.core.domain.models.Resource
import com.example.feature.newslist.domain.api.GetNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val newsItemDao: NewsItemDao
) : GetNewsRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getNewsItems(): Flow<Resource<List<Item>>> {
        val request = NewsItemsRequest()

        return handleResponse<NewsItemsResponse> {
            networkClient.doRequest(request)
        }.flatMapLatest { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.newsItems?.let { saveNewsToDb(it) }

                    getNewsFromDb().map { items ->
                        Resource.Success(items)
                    }
                }

                is Resource.Error -> {
                    getNewsFromDb().map { items ->
                        Resource.Error(resource.error ?: ErrorType.UNKNOWN_ERROR, data = items)
                    }
                }
            }
        }
    }

    private fun getNewsFromDb(): Flow<List<Item>> =
        newsItemDao.getAllNewsItem()
            .map { list ->
                list.map { it.toDomain() }
            }
            .catch { e ->
                Log.e(TAG, "Error get news from DB, exception -> ${e.localizedMessage}")
                emit(emptyList())
            }

    private suspend fun saveNewsToDb(newsList: List<ItemDto>) = withContext(Dispatchers.IO) {
        val entities = newsList.map { it.mapToEntity() }
        runCatching {
            newsItemDao.insertItems(entities)
        }.onFailure { e ->
            Log.e(TAG, "Error on save news to DB, exception -> ${e.localizedMessage}")
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private inline fun <reified T> handleResponse(
        crossinline functionToHandle: suspend () -> Response
    ): Flow<Resource<T>> = flow {
        try {
            val response = functionToHandle()
            when (val code = response.resultCode) {
                StatusCode(0) -> {
                    val data = response as? T
                        ?: throw ClassCastException("It Is Impossible To Transform The Result To ${T::class}")
                    emit(Resource.Success(data))
                }

                else -> emit(Resource.Error(code.mapToErrorType()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.NO_CONNECTION))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.BAD_REQUEST))
        } catch (e: RuntimeException) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.BAD_REQUEST))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(ErrorType.SERVER_ERROR))
        }
    }

    private companion object {
        val TAG = GetNewsRepositoryImpl::class.simpleName
    }
}