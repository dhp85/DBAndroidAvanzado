package com.keepcoding.dbandroidavanzado.presentation.ui.heros

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryHeros
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel
import com.keepcoding.dbandroidavanzado.presentation.ui.heros.model.HomeState
import com.keepcoding.dbandroidavanzado.presentation.ui.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RepositoryHeros) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun init(context: Context) {
        repository.init(context)
    }

    fun getSuperHeros() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = HomeState.Loading
            val superHeros = repository.getHeros().map { it.toHeroModel() }
            if (superHeros.isEmpty()) {
                _state.value = HomeState.Error("Error getting heros")
            }
            _state.value = HomeState.Success(superHeros)

        }
    }
}