package com.picpay.desafio.android.repository

import com.picpay.desafio.android.network.response.User

class UseCase(private val picPayRepository: PicPayRepository) {
    suspend fun getUsers(): List<User> {
        return picPayRepository.getUsers()
    }
}