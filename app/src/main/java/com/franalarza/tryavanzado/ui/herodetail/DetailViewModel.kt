package com.franalarza.tryavanzado.ui.herodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _heroDetail = MutableLiveData<HeroLocalState>()
    val liveHeroDetail: LiveData<HeroLocalState>
        get() = _heroDetail

    private val _heroLocation = MutableLiveData<LocationState>()
    val liveHeroLocation: LiveData<LocationState>
        get() = _heroLocation

    fun getHeroDetail(name: String) {
        viewModelScope.launch {
            val heroDetail = repository.getHeroesDetail(name)
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

    fun saveHeroInLocal(hero: HeroLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveHeroFromLocal(hero)
        }

    }

    fun getHeroFromLocal(name: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val heroLocalDetail = repository.getHeroFromLocal(name, id)
            withContext(Dispatchers.Main) {
                _heroDetail.value = heroLocalDetail
            }
        }
    }

    fun toggleFavoriteLocal(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteLocal(id)
        }
    }
}