package com.myschool.shiftcft.util

interface Callback<T> {

    fun onSuccess(data: T)

    fun onError(throwable: Throwable)
}