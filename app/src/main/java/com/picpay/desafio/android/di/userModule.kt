package com.picpay.desafio.android.di

import com.picpay.desafio.android.repository.UseCase
import com.picpay.desafio.android.repository.UseCaseImpl
import com.picpay.desafio.android.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { HomeViewModel(get()) }
    factory<UseCase> { UseCaseImpl(get()) }
}