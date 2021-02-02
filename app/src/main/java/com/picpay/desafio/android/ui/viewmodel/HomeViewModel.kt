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

    private val userList = ArrayList<User>()
    val homeLiveData = MutableLiveData<LiveDataResult<MutableList<User>>>()

    fun getUsers() {
        viewModelScope.launch {

            homeLiveData.value = LiveDataResult.loading()
            userList.clear()

            try {
                val result = useCase.getUsers()
                userList.addAll(result)

                homeLiveData.value = LiveDataResult.success(userList)

            }catch (e: Exception) {
                homeLiveData.value = LiveDataResult.error(e)
            }
        }
    }
}