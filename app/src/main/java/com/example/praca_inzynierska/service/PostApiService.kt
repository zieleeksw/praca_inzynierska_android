package com.example.praca_inzynierska.service

import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.requests.FollowPostRequest
import com.example.praca_inzynierska.requests.PostRequest
import com.example.praca_inzynierska.service.Retrofit.retrofit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val postService: PostApiService = retrofit.create(PostApiService::class.java)

interface PostApiService {

    @GET("/api/posts")
    suspend fun fetchAllPosts(@Header("Authorization") authorization: String): List<Post>

    @POST("/api/posts/add")
    suspend fun addPost(
        @Header("Authorization") authorization: String,
        @Body postRequest: PostRequest
    ): Response<Void>

    @POST("/api/posts/follow")
    suspend fun followPost(
        @Header("Authorization") authorization: String,
        @Body followPostRequest: FollowPostRequest
    ): Response<Void>

    @DELETE("/api/posts/{id}")
    suspend fun deletePost(
        @Header("Authorization") authorization: String,
        @Path("id") postId: Long
    ): Response<Void>
}