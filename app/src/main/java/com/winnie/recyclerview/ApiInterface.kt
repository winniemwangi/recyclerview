package com.winnie.recyclerview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("comments")
    fun getComments(): Call<List<Comment>>
}