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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RepositoryHeros
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getSuperHeros() {
        viewModelScope.launch {  // Inicia una corrutina en el hilo principal (Main)
            _state.value = HomeState.Loading  // Notifica a la UI que los datos se están cargando

            val superHeros = withContext(Dispatchers.IO) {  //  Cambia a un hilo de E/S (Input/Output)
                repository.getHeros().map { it.toHeroModel() } // Obtiene los datos de la BD o red
            }
            _state.value = if (superHeros.isEmpty()) {  // Regresa al hilo principal y actualiza la UI
                HomeState.Error("Error getting heros")  //  Si no hay datos, muestra un error
            } else {
                HomeState.Success(superHeros)  // Si hay datos, actualiza la UI con la lista de héroes
            }
        }
    }

    //He tenido que crear esta funcion porque en el fragment cuando volvia del detalle
    // me hacia la lista con duplicados. En el lifecycleScope he tenido que utilizar Viewlifecycleowner para que no diera errores.
    fun clearViewModel() {
            _state.value = HomeState.Success(emptyList())
    }
}