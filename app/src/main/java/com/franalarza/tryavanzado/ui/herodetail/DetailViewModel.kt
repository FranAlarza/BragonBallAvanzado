package com.franalarza.tryavanzado.ui.herodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _heroDetail = MutableLiveData<HeroDetailStatus>()
    val liveHeroDetail: LiveData<HeroDetailStatus>
        get() = _heroDetail

    private val _heroLocation = MutableLiveData<LocationState>()
    val liveHeroLocation: LiveData<LocationState>
        get() = _heroLocation

    fun getHeroDetail(name: String) {
        viewModelScope.launch {
            val heroDetail = repository.getHeroesDetail(name)
            _heroDetail.value = heroDetail
        }
    }

    fun getHeroLocation(id: String) {
        viewModelScope.launch {
            val heroLocation = repository.getLocations(id)
            _heroLocation.value = heroLocation
        }
    }

    fun toogleFavorite(hero: String) {
        viewModelScope.launch {
            repository.toogleFavorite(hero)
        }
    }
}