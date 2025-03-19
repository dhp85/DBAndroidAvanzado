package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryDetail
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import com.keepcoding.dbandroidavanzado.presentation.ui.detail.model.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RepositoryDetail) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailState>(DetailState.Idle)
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    private val _hero = MutableLiveData<List<HeroModel>>()
    val hero: LiveData<List<HeroModel>> get() = _hero

    fun getHero(name: String) {
        _uiState.value = DetailState.Loading
        viewModelScope.launch {
            val data = repository.getHero(name).map { it.toHeroModel() }
            _hero.value = data
            _uiState.value = DetailState.Success
        }
    }
}