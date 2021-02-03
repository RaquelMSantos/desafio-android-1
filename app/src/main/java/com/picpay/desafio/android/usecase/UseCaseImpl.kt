package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.repository.PicPayRepository

class UseCaseImpl(private val picPayRepository: PicPayRepository):
    UseCase {
    override suspend fun getUsers() = picPayRepository.getUsers()
}