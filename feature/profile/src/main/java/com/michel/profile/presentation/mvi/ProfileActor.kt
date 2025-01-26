package com.michel.profile.presentation.mvi

import com.michel.mvi.store.Actor
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.profile.presentation.composables.menu.MenuItemType
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ProfileActor @Inject constructor() : Actor<ProfileIntent, ProfileState, ProfileMessage> {

    override fun run(intent: ProfileIntent, prevState: ProfileState): Flow<ProfileMessage> =
        when (intent) {
            is ProfileIntent.BackClicked -> flowOf(ProfileMessage.Navigate(ProfileNavDirection.Up))
            is ProfileIntent.MenuItemClicked -> handleMenuClick(intent.itemType)
        }

    private fun handleMenuClick(itemType: MenuItemType) = flowOf(
        when(itemType) {
            MenuItemType.SETTINGS -> ProfileMessage.Navigate(ProfileNavDirection.Settings)
            MenuItemType.APP_INFO -> ProfileMessage.Navigate(ProfileNavDirection.AppInfo)
        }
    )
}
