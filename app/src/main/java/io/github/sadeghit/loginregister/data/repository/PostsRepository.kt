package io.github.sadeghit.loginregister.data.repository

import io.github.sadeghit.loginregister.data.remote.ApiService
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiRepository: ApiRepository,

    ) {

    suspend fun getPosts() =
        apiRepository.safeGetData {
            apiService.getPosts()
        }
}