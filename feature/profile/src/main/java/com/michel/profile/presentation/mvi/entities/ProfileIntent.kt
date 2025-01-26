package com.michel.profile.presentation.mvi.entities

import com.michel.profile.presentation.composables.menu.MenuItemType

sealed interface ProfileIntent {
    data object BackClicked : ProfileIntent
    data class MenuItemClicked(val itemType: MenuItemType) : ProfileIntent
}
