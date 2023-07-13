package com.example.memepicturesapp.data

import com.example.memepicturesapp.models.MemeList
import retrofit2.Response
import retrofit2.http.GET

interface Apinterface {


    @GET("get_memes")
    suspend fun getMemes():Response<MemeList>
}