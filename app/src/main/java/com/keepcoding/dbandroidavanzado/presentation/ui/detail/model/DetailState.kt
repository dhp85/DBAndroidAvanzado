package com.keepcoding.dbandroidavanzado.presentation.ui.detail.model

import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel


sealed class DetailState{
    data object Loading: DetailState()
    data object Success: DetailState()
    data class Error(val message: String): DetailState()
}
