package io.kdbrian.urbandict.features.auth

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthEvent(
    private val event: AuthenticationEvent
) {

    @Serializable
    data object GetStarted : AuthEvent(AuthenticationEvent.GET_STARTED)

    @Serializable
    data class SignIn(val email: String, val password: String) :
        AuthEvent(AuthenticationEvent.SIGN_IN)

    @Serializable
    data class SignUp(val email: String, val password: String) :
        AuthEvent(AuthenticationEvent.SIGN_UP)

    @Serializable
    data object PasswordLess : AuthEvent(AuthenticationEvent.PASSWORD_LESS)

    @Serializable
    data object SignOut : AuthEvent(AuthenticationEvent.SIGN_OUT)

    private enum class AuthenticationEvent {
        GET_STARTED,
        PASSWORD_LESS,
        SIGN_IN,
        SIGN_UP,
        SIGN_OUT
    }

}