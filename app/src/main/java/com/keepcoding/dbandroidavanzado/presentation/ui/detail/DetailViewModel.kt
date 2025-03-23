package com.keepcoding.dbandroidavanzado.presentation.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryDetail
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryFavorite
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryLocations
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModel
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
    private val repositoryLocations: RepositoryLocations,
    private val repositoryFavorite: RepositoryFavorite
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailState>(DetailState.Loading)
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    private val _hero = MutableLiveData<List<HeroModel>>()
    val hero: LiveData<List<HeroModel>> get() = _hero

    private val _locations = MutableLiveData<List<LocationModel>>()
    val locations: LiveData<List<LocationModel>> get() = _locations

    val favorite: LiveData<Boolean> = _hero.map { heroes ->
        heroes.firstOrNull()?.favorite ?: false
    }


    fun getHero(name: String) {
        viewModelScope.launch {
            val heroResult = async(Dispatchers.IO) {
                val dataHero = repository.getHero(name)
                dataHero.onSuccess { data ->
                    _hero.postValue(data.map { it.toHeroModel() })
                }.onFailure { throwable ->
                    _uiState.value = DetailState.Error(throwable.message ?: "Error app")
                }
            }
            heroResult.await()
            _uiState.value = DetailState.Success
        }
    }

    fun getLocations(id: String) {
        _uiState.value = DetailState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val locationsResult = async(Dispatchers.IO) {
                repositoryLocations.getLocations(id)
                    .onSuccess { data ->
                        _locations.postValue(data.map { it.toLocationModel() })
                    }.onFailure { throwable ->
                        _uiState.value = DetailState.Error(throwable.message ?: "Error app")
                    }
            }
            locationsResult.await()
            _uiState.value = DetailState.Success
        }
    }

    fun isFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryFavorite.toggleFavorite(id)
           _hero.postValue(_hero.value?.map { it.copy(favorite = !it.favorite) })
        }
    }
}