package com.sbusraoner.recipeapp.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

val gson = Gson()

fun <T> apiFlow(
    call: suspend () -> Response<T>?
): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)
    try {
        Log.e("ApiFlow", "apiFlow")
        val c = call()
        c?.let {
            Log.e("ApiFlow", "apiFlow c: ${c.isSuccessful}")
            if (c.isSuccessful) {
                Log.e("ApiFlow", "apiFlow c.body: ${c.body()}")
                emit(ApiResult.Success(c.body()))
            } else {
                val errorBody = c.errorBody()
              //  val errorResponse = gson.fromJson(errorBody, ApiErrorResponse::class.java)
               // emit(ApiResult.Error(errorResponse.message ?: "Api response error"))
            }
        }
    } catch (e: Exception) {
        Log.e("ApiFlow", e.message ?: "An error occurred")
        emit(ApiResult.Error(e.message ?: "An error occurred"))
    }
}.flowOn(Dispatchers.IO)

sealed class ApiResult<out T> {
    data class Success<out R>(val data: R?) : ApiResult<R>()
    data class Error(val message: String) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}

data class ApiErrorResponse(
    //@SerializedName("status")
    //val status :String?,
    @SerializedName("code")
    val code :Int?,
 //   @SerializedName("message")
   // val message:String?,
)