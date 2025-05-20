package com.example.feature.newslist.data

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : GetNewsRepository {
    override fun getNewsItems(): Flow<Resource<List<Item>>> {
        val request = NewsItemsRequest()

        return handleResponse<NewsItemsResponse> {
            networkClient.doRequest(request)
        }.map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val items = resource.data?.newsItems?.map { it.toDomain() }.orEmpty()
                    Resource.Success(items)
                }

                is Resource.Error -> Resource.Error(resource.error ?: ErrorType.UNKNOWN_ERROR)
            }
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
}