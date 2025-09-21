package com.myschool.shiftcft.util

sealed class NetworkState {
    object Connected: NetworkState()
    object Disconnected: NetworkState()
    object Restored: NetworkState()


}