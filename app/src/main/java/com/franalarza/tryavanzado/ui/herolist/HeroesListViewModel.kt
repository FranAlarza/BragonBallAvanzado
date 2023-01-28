package com.franalarza.tryavanzado.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franalarza.tryavanzado.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _heroes = MutableLiveData<HeroesState>()
    val liveHeroes: LiveData<HeroesState>
        get() = _heroes

    fun getHeroes() {
        viewModelScope.launch {
            val heroes = withContext(Dispatchers.IO) {
                repository.getHeroesFromLocal()
            }
            _heroes.value = heroes
        }
    }

}