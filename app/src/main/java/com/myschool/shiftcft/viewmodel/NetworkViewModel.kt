package com.myschool.shiftcft.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myschool.shiftcft.model.NetworkState
import com.myschool.shiftcft.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {
    private val _networkState = MutableStateFlow<NetworkState>(NetworkState.Connected)
    val networkState: StateFlow<NetworkState> = _networkState.asStateFlow()

    init{
        observeNetworkStatus()
    }

    private fun observeNetworkStatus() {
        viewModelScope.launch {
            networkMonitor.networkStatus.collect { status ->
                val previousState = _networkState.value
                _networkState.value = when {
                    status && previousState is NetworkState.Disconnected -> NetworkState.Restored
                    status -> NetworkState.Connected
                    else -> NetworkState.Disconnected
                }
            }
        }
    }
}