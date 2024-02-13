package com.example.praca_inzynierska.forum.services

import com.example.praca_inzynierska.forum.data.Comment
import com.example.praca_inzynierska.forum.requests.CommentRequest
import com.example.praca_inzynierska.commons.services.Retrofit.retrofit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val commentService: CommentService = retrofit.create(CommentService::class.java)

interface CommentService {

    @GET("/api/comments/{id}")
    suspend fun fetchAllComments(
        @Header("Authorization") authorization: String,
        @Path("id") postId: Long
    ): List<Comment>

    @POST("/api/comments")
    suspend fun addComment(
        @Header("Authorization") authorization: String,
        @Body commentRequest: CommentRequest
    ): Response<Void>

    @DELETE("/api/comments/{id}")
    suspend fun deleteComment(
        @Header("Authorization") authorization: String,
        @Path("id") postId: Long
    ): Response<Void>
}