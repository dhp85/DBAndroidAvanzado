package com.keepcoding.dbandroidavanzado.presentation.ui.heros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.RepositoryHeros
import com.keepcoding.dbandroidavanzado.entities.HeroModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state: MutableStateFlow<List<HeroModel>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<HeroModel>> = _state.asStateFlow()

    private val repository = RepositoryHeros()

    init {
        getSuperHeros()
    }

    fun getSuperHeros() {
        viewModelScope.launch {
            val superHeros = repository.getHeros().map { it.toHeroModel() }
            _state.value = superHeros
        }
    }

}