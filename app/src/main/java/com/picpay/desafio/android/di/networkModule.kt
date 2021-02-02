package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.service.provideOkHttpClient
import com.picpay.desafio.android.network.service.providePicPayService
import com.picpay.desafio.android.network.service.provideRetrofit
import org.koin.dsl.module

val networkModule = module{
    single { provideOkHttpClient() }
    single { providePicPayService(get()) }
    single { provideRetrofit(get()) }
}