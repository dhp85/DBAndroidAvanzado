package com.keepcoding.dbandroidavanzado.presentation.ui.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryLogin
import com.keepcoding.dbandroidavanzado.presentation.ui.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RepositoryLogin): ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()


    fun login(user: String, pass: String){
        _uiState.value = LoginState.Loading
        viewModelScope.launch(Dispatchers.IO) {
           val result =  repository.login(user,pass)
            result.onSuccess {
                _uiState.value = LoginState.Success
            }.onFailure { throwable ->
                _uiState.value = LoginState.Error(throwable.message ?: "Error")
            }
        }
    }
}