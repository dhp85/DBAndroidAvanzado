package com.keepcoding.dbandroidavanzado.presentation.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryHeros
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel
import com.keepcoding.dbandroidavanzado.presentation.ui.heros.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RepositoryHeros) : ViewModel() {


}