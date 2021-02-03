package com.picpay.desafio.android.util

import com.picpay.desafio.android.model.User

data class LiveDataState (val status: STATUS, val data: List<User>? = null, val error: Throwable? = null) {
    enum class STATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun success(data: List<User>) =
            LiveDataState(
                STATUS.SUCCESS,
                data
            )
        fun error(err: Throwable) =
            LiveDataState(
                STATUS.ERROR,
                null,
                err
            )
        fun loading() =
            LiveDataState(STATUS.LOADING)
    }
}