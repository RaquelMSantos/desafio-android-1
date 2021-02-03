package com.picpay.desafio.android.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.usecase.UseCase
import com.picpay.desafio.android.util.LiveDataState
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewModel (
    private val useCase: UseCase
): ViewModel() {

    private val _users = MutableLiveData<LiveDataState>()
    val user: MutableLiveData<LiveDataState>
        get() = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {

            _users.value = LiveDataState.loading()

            try {
                val result = useCase.getUsers()
                _users.value = LiveDataState.success(result)

            }catch (e: Exception) {
                _users.value = LiveDataState.error(e)
            }
        }
    }
}