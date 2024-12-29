package com.michel.profile.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import javax.inject.Inject

class ProfileReducer @Inject constructor() : Reducer<ProfileEffect, ProfileState, ProfileMessage> {
    override fun reduce(message: ProfileMessage, prevState: ProfileState): ProfileState {
        TODO("Not yet implemented")
    }
}
