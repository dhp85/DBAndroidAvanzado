package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryDetail
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryLocations
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import com.keepcoding.dbandroidavanzado.domain.entities.LocationModel
import com.keepcoding.dbandroidavanzado.presentation.ui.detail.model.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RepositoryDetail,
    private val repositoryLocations: RepositoryLocations
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailState>(DetailState.Loading)
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    private val _hero = MutableLiveData<List<HeroModel>>()
    val hero: LiveData<List<HeroModel>> get() = _hero

    private val _locations = MutableLiveData<List<LocationModel>>()
    val locations: LiveData<List<LocationModel>> get() = _locations


    fun getHero(name: String) {
        viewModelScope.launch {
            try {
                val heroResult = async(Dispatchers.IO) {
                    val dataHero = repository.getHero(name).map { it.toHeroModel() }
                    _hero.postValue(dataHero)
                }
                heroResult.await()
                _uiState.value = DetailState.Success
            } catch (e: Exception) {
                _uiState.value = DetailState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun getLocations(id: String) {
        _uiState.value = DetailState.Loading
        viewModelScope.launch {
            try {
                val locationsResult = async(Dispatchers.IO) {
                    val dataLocations = repositoryLocations.getLocations(id).map { it.toLocationModel() }
                    _locations.postValue(dataLocations)
                }
                locationsResult.await()
                _uiState.value = DetailState.Success
            } catch (e: Exception) {
                _uiState.value = DetailState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}