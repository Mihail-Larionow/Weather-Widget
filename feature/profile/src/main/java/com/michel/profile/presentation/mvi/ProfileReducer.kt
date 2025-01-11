package com.michel.profile.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import javax.inject.Inject

class ProfileReducer @Inject constructor() : Reducer<ProfileEffect, ProfileState, ProfileMessage> {
    override fun reduce(message: ProfileMessage, prevState: ProfileState): ProfileState =
        when (message) {
            is ProfileMessage.Navigate,
            is ProfileMessage.Empty,
                -> prevState
        }

    override fun reduceEffect(message: ProfileMessage): ProfileEffect? = when (message) {
        is ProfileMessage.Navigate -> ProfileEffect.Navigate(message.direction)
        is ProfileMessage.Empty,
            -> Reducer.nothing
    }
}
