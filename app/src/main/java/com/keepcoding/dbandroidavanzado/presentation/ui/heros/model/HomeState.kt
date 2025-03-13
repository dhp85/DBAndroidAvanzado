package com.keepcoding.dbandroidavanzado.presentation.ui.heros.model

import com.keepcoding.dbandroidavanzado.entities.HeroModel

sealed class HomeState{
    data object Loading: HomeState()
    data class Success(val heros: List<HeroModel>): HomeState()
    data class Error(val message: String): HomeState()
}
