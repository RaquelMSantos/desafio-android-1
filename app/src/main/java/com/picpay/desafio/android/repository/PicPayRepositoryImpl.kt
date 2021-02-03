package com.picpay.desafio.android.repository

import com.picpay.desafio.android.network.PicPayService

class PicPayRepositoryImpl (
    private val picPayService: PicPayService
): PicPayRepository {

    override suspend fun getUsers() =
        picPayService.getUsers()
    }