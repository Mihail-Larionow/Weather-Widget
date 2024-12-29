package com.michel.navigation.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.michel.feature.navigation.R
import com.michel.mvi.MviNavFragment
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.base.FeatureNavApi
import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavPresentationSubcomponent
import com.michel.navigation.presentation.mvi.entities.NavEffect
import com.michel.navigation.presentation.mvi.entities.NavIntent
import com.michel.navigation.presentation.mvi.entities.NavMessage
import com.michel.navigation.presentation.mvi.entities.NavState
import com.michel.navigation.presentation.navcontroller.NavControllerFragment
import com.michel.navigation.presentation.navgraph.nodes.createWeatherNavGraph
import com.michel.navigation.presentation.navigation.MainNavDirection
import javax.inject.Inject

class NavFragment : MviNavFragment<
        NavIntent,
        NavEffect,
        NavState,
        NavMessage,
        >(R.layout.fragment_nav), NavControllerFragment {

    @Inject
    lateinit var navApi: FeatureNavApi<MainNavDirection>

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<NavIntent, NavEffect, NavState, NavMessage>

    private val navHostFragment: NavHostFragment by lazy {
        val fragment = childFragmentManager.findFragmentById(R.id.nav_host)
        if (fragment is NavHostFragment) {
            fragment.apply { setupGraph() }
        } else {
            error("NavHostFragment not found")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        NavComponentHolder
            .getSubcomponent<NavPresentationSubcomponent>(PRESENTATION_SUBCOMPONENT_DATA)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewListeners()
        navHostFragment
        setupBackButton()
    }

    private fun NavHostFragment.setupGraph() {
        navController.graph = navController.createWeatherNavGraph()
    }

    override fun onDetach() {
        NavComponentHolder
            .clearSubcomponent<NavPresentationSubcomponent>(PRESENTATION_SUBCOMPONENT_DATA)
        super.onDetach()
    }

    private fun bindViewListeners() {

    }

    override fun handleEffect(effect: NavEffect) {
        when (effect) {
            is NavEffect.Navigate -> navApi.navigate(effect.direction)
        }
    }

    override fun render(state: NavState) {

    }

    override fun getNavController(): NavController? = try {
        navHostFragment.navController
    } catch (exception: IllegalStateException) {
        null
    }

    override fun navigateUp() {
        viewModel.accept(NavIntent.NavigateUp)
    }

    private fun setupBackButton() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateUp()
                }
            }
        )
    }

    companion object {

        private const val PRESENTATION_SUBCOMPONENT_DATA = 0

        fun newInstance(): Fragment =
            NavFragment().apply {
                arguments = bundleOf(
                    com.michel.utils.extensions.view.ARGS_KEY to NavFragmentArgs(),
                )
            }

    }
}
