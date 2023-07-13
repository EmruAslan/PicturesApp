package com.example.memepicturesapp.utils

import com.example.memepicturesapp.data.Apinterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {

    val api:Apinterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.Base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apinterface::class.java)
    }
}