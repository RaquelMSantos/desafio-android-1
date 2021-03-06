package com.picpay.desafio.android.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.android.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

fun provideOkHttpClient(cache: Cache, application: Application): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(application)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=5").build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            chain.proceed(request)
        }
        .build()
}

private fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    activeNetwork?.let { isConnected = it.isConnected }
    return isConnected
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun providePicPayService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)