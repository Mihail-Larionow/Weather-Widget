package com.michel.profile.presentation.mvi

import com.michel.mvi.store.Actor
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileActor @Inject constructor() : Actor<ProfileIntent, ProfileState, ProfileMessage> {
    override fun run(intent: ProfileIntent, prevState: ProfileState): Flow<ProfileMessage> {
        TODO("Not yet implemented")
    }
}
