package io.github.sadeghit.loginregister.data.repository

import android.util.Log
import com.google.gson.JsonSyntaxException
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor() {

    suspend fun <T> safeGetData(request: suspend () -> T): Result<T> {
        return try {
            val response = request()
            Result.success(response)
        }catch (e: Exception){
            when (e){
                is SocketTimeoutException -> Log.e("TAG","TimeOut")
                is IOException -> Log.e("TAG","Connection Error")
                is HttpException -> Log.e("TAG","HTTP ${e.code()} : ${e.message()}")
                is JsonSyntaxException -> Log.e("TAG", "Malformed Json")
                else -> Log.e("TAG","Unknowing Error : ${e.message}")
            }
            Result.failure(e)
        }
    }
}