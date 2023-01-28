package com.franalarza.tryavanzado.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _token = MutableLiveData<String>()
    val liveToken: LiveData<String>
        get() = _token


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val tokenResponse = withContext(Dispatchers.IO) {
                repository.getToken(email, password)
            }
            Log.d("Token", repository.getToken(email, password))
            _token.value = tokenResponse
        }

    }

    fun checkToken(token: String): Boolean{
         return token.isNotEmpty()
    }

}