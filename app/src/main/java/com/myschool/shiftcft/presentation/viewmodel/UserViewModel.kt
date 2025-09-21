package com.myschool.shiftcft.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myschool.shiftcft.domain.model.User
import com.myschool.shiftcft.data.remote.NetworkUsersRepositoryImpl
import com.myschool.shiftcft.data.local.DbUsersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val roomRepository: DbUsersRepositoryImpl,
    private val networkRepository: NetworkUsersRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        roomRepository.getUsers()
            .onEach { users ->
                _uiState.update {
                    it.copy(user = users)
                }
            }.launchIn(viewModelScope)
    }
    fun saveUsers(users: List<User>) {
        viewModelScope.launch {
            roomRepository.saveUser(users)
        }

    }
    fun getUsers(count: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val users = networkRepository.getUsers(count)
                saveUsers(users)
            } catch (e: Exception) {
                Log.e("UserViewModel", "error with download")
                _errorMessage.emit("Ошибка")
            } finally {
                _isLoading.value = false
            }

        }

    }
    fun deleteAllUsers() {
        viewModelScope.launch {
            roomRepository.deleteAll()
        }

    }
    fun isEmpty(callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = roomRepository.isEmpty()
            callback(result)
        }
    }
}