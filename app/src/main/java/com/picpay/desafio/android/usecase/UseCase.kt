package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.model.User

interface UseCase {
    suspend fun getUsers(): List<User>
}