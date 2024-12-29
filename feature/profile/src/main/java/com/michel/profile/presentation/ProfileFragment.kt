package com.michel.profile.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.michel.mvi.MviFragment
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.base.FeatureNavApi
import com.michel.profile.di.ProfileComponentHolder
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.profile.presentation.composables.ProfileScreenContent
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import javax.inject.Inject
import kotlin.reflect.KClass

val ProfileFragmentKClass: KClass<out Fragment> = ProfileFragment::class

class ProfileFragment : MviFragment<
        ProfileIntent,
        ProfileEffect,
        ProfileState,
        ProfileMessage,
        >() {

    @Inject
    lateinit var navApi: FeatureNavApi<ProfileNavDirection>

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProfileComponentHolder
            .get()
            .inject(this)
    }

    override fun onDetach() {
        if (isRemoving) {
            ProfileComponentHolder.clear()
        }
        super.onDetach()
    }

    @Composable
    override fun Render(state: ProfileState) {
        ProfileScreenContent()
    }

    override fun handleEffect(effect: ProfileEffect) {
        when (effect) {
            is ProfileEffect.Navigate -> navApi.navigate(effect.direction)
        }
    }
}
