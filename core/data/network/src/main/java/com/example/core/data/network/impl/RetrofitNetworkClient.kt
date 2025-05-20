package com.example.core.data.network.impl

import android.content.Context
import com.example.core.data.network.api.NetworkClient
import com.example.core.data.network.api.NewsApi
import com.example.core.data.network.models.NetworkParams
import com.example.core.data.network.models.Response
import com.example.core.data.network.models.StatusCode
import com.example.core.data.network.models.request.NewsItemsRequest
import com.example.core.data.network.models.response.NewsItemsResponse
import com.example.core.utils.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val apiService: NewsApi,
    private val context: Context
) : NetworkClient {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun doRequest(dto: Any): Response {
        if (context.isInternetAvailable().not()) {
            return Response().apply {
                resultCode =
                    StatusCode(NetworkParams.NO_CONNECTION_CODE)
            }
        }
        return withContext(Dispatchers.IO) {
            try {
                doRequestInternal(dto)
            } catch (e: IOException) {
                e.printStackTrace()
                Response().apply { resultCode = StatusCode(NetworkParams.NO_CONNECTION_CODE) }
            } catch (e: HttpException) {
                e.printStackTrace()
                getHttpExceptionResponse()
            } catch (e: RuntimeException) {
                e.printStackTrace()
                getRuntimeExceptionResponse()
            } catch (e: Exception) {
                e.printStackTrace()
                Response().apply { resultCode = StatusCode(NetworkParams.SERVER_ERROR_CODE) }
            }
        }
    }

    private suspend fun doRequestInternal(dto: Any): Response {
        return when (dto) {
            is NewsItemsRequest -> return newsItemsRequest()

            else -> Response().apply { resultCode = StatusCode(NetworkParams.BAD_REQUEST_CODE) }
        }
    }

    private suspend fun newsItemsRequest(): NewsItemsResponse {
        val rssDto = apiService.fetchRss()
        return NewsItemsResponse(newsItems = rssDto.channel.items).apply {
            resultCode = StatusCode(0)
        }
    }

    private fun getHttpExceptionResponse(): Response {
        return Response().apply { resultCode = StatusCode(NetworkParams.BAD_REQUEST_CODE) }
    }

    private fun getRuntimeExceptionResponse(): Response {
        return Response().apply { resultCode = StatusCode(NetworkParams.BAD_REQUEST_CODE) }
    }
}