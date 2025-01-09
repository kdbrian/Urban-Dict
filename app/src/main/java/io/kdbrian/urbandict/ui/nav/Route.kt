package io.kdbrian.urbandict.ui.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object OnBoarding : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data class ViewWord(val wordId: String) : Route()

    @Serializable
    data class Account(val userId: String) : Route()
}