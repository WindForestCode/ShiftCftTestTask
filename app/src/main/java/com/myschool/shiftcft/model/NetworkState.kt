package com.myschool.shiftcft.model

sealed class NetworkState {
    object Connected: NetworkState()
    object Disconnected: NetworkState()
    object Restored: NetworkState()


}