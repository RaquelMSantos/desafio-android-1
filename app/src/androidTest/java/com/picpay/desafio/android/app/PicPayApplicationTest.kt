package com.picpay.desafio.android.app

import android.app.Application
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.userModule
import org.junit.Assert.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplicationTest: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplicationTest)
            modules(listOf(networkModule, userModule))
        }
    }
}