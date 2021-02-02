package com.picpay.desafio.android.app

import android.app.Application
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(listOf(
                networkModule,
                userModule
            ))
        }
    }
}