package io.github.sadeghit.loginregister.data.remote

import io.github.sadeghit.loginregister.data.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
