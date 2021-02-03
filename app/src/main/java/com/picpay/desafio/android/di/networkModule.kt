package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.provideCache
import com.picpay.desafio.android.network.provideOkHttpClient
import com.picpay.desafio.android.network.providePicPayService
import com.picpay.desafio.android.network.provideRetrofit
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.repository.PicPayRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module{
    single { provideCache(androidApplication()) }
    single { provideOkHttpClient(get(), androidApplication()) }
    single { providePicPayService(get()) }
    single { provideRetrofit(get()) }
    single<PicPayRepository> { PicPayRepositoryImpl(get()) }
}

