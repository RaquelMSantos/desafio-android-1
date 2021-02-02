package com.picpay.desafio.android.network.service

import com.picpay.desafio.android.network.response.User
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    fun getUsers(): List<User>
}