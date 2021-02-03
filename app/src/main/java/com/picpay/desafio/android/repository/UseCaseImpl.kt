package com.picpay.desafio.android.repository

class UseCaseImpl(private val picPayRepository: PicPayRepository): UseCase {
    override suspend fun getUsers() = picPayRepository.getUsers()
}