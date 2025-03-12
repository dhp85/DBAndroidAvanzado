package com.keepcoding.dbandroidavanzado.presentation.ui.heros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keepcoding.dbandroidavanzado.entities.HeroModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _state: MutableStateFlow<List<HeroModel>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<HeroModel>> = _state.asStateFlow()

    init {
        getSuperHeros()
    }

    fun getSuperHeros() {
        _state.value = superHeros

    }

    private val superHeros: MutableList<HeroModel> = mutableListOf (HeroModel(
        id = "1",
        name = "Goku",
        photo = "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300",
        description = "Goku es un super heroe",
        favorite = false),
            HeroModel(
                id = "2",
                name = "Vegeta",
                photo = "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/vegetita.jpg?width=300",
                description = "Vegeta es un super heroe",
                favorite = false
            ))

}