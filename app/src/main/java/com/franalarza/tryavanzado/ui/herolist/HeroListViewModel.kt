package com.franalarza.tryavanzado.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franalarza.tryavanzado.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class HeroListViewModel: ViewModel() {

    private val repository = Repository()

    fun getToken() {
        viewModelScope.launch {
            val token = withContext(Dispatchers.IO) {
                repository.getToken()
            }
        }
    }
}