package com.picpay.desafio.android.network.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()
}

fun providePicPayService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)