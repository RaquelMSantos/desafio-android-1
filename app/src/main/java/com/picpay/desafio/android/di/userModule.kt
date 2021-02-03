package com.picpay.desafio.android.di

import com.picpay.desafio.android.usecase.UseCase
import com.picpay.desafio.android.usecase.UseCaseImpl
import com.picpay.desafio.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { HomeViewModel(get()) }
    factory<UseCase> { UseCaseImpl(get()) }
}