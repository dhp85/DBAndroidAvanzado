package com.keepcoding.dbandroidavanzado.presentation.ui.heros

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryHeros
import com.keepcoding.dbandroidavanzado.entities.HeroModel
import com.keepcoding.dbandroidavanzado.presentation.ui.heros.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val repository = RepositoryHeros()

    fun init(context: Context) {
        repository.init(context)
    }


    fun getSuperHeros() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            val superHeros = repository.getHeros().map { it.toHeroModel() }

            _state.value = HomeState.Success(superHeros)

        }
    }

}