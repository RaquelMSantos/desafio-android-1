package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.network.response.User
import com.picpay.desafio.android.repository.*
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewModel (
    private val useCase: UseCase
): ViewModel() {

    private val _users = MutableLiveData<LiveDataResult<MutableList<User>>>()
    val user: MutableLiveData<LiveDataResult<MutableList<User>>>
        get() = _users

    private lateinit var userList: ArrayList<User>

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {

            _users.value = LiveDataResult.loading()

            try {

                val result = useCase.getUsers()
                userList.addAll(result)

                _users.value = LiveDataResult.success(userList)

            }catch (e: Exception) {
                _users.value = LiveDataResult.error(e)
            }
        }
    }
}