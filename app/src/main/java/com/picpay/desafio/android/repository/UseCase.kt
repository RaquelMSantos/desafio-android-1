package com.picpay.desafio.android.repository

import com.picpay.desafio.android.network.response.User

interface UseCase {
    suspend fun getUsers(): List<User>
}