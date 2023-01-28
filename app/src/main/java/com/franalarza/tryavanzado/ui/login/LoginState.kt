package com.franalarza.tryavanzado.ui.login

sealed class LoginState {
    data class Success(val token: String): LoginState()
    data class Failure(val ErrorMessage: String): LoginState()
}
