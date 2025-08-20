package com.myschool.shiftcft.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.repository.RoomUsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val roomRepository: RoomUsersRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

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