package com.michel.mvi

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.michel.mvi.compose.EdgeToEdgeDialog
import com.michel.mvi.extensions.collectWhenStarted
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.ui.R
import com.michel.ui.composables.bottomsheet.BottomSheetCornerStyle
import com.michel.ui.composables.bottomsheet.WeatherModalBottomSheet
import com.michel.ui.theme.WeatherTheme
import com.michel.utils.ViewModelFactoryProvider
import kotlinx.coroutines.channels.Channel

abstract class MviBottomSheet<I, E, S, M> : DialogFragment() {

    protected abstract val viewModelFactory: StoreViewModelFactory<I, E, S, M>

    protected val viewModel: StoreViewModel<I, E, S, M> by viewModels {
        ViewModelFactoryProvider.provide(viewModelFactory::create)
    }

    protected open val isFullscreen: Boolean = false

    protected var bottomSheetConfirmValueChangeCallback: (ModalBottomSheetValue) -> Boolean =
        { true }

    private var isShown: Boolean = false

    private var onBackPressedCallback: OnBackPressedCallback? = null

    private val dismissChannel: Channel<Unit> = Channel()

    protected open val sheetCornersStyle: BottomSheetCornerStyle =
        BottomSheetCornerStyle.StandardCornerStyle

    override fun getTheme(): Int = if (isFullscreen) {
        R.style.Theme_Weather_FullscreenDialog
    } else {
        R.style.Theme_Weather_Dialog
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        EdgeToEdgeDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsState()
                WeatherTheme {
                    ModalBottomSheet {
                        Render(state = state)
                    }
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectWhenStarted(viewModel, effectConsumer = ::handleEffect)
        setOnBackPressedCallback {
            if (bottomSheetConfirmValueChangeCallback(ModalBottomSheetValue.Hidden)) {
                dismiss()
            }
        }
    }

    override fun dismiss() {
        dismissChannel.trySend(Unit)
    }

    private fun setOnBackPressedCallback(onBackPressedAction: (() -> Unit)?) {
        onBackPressedCallback?.remove()
        onBackPressedCallback = onBackPressedAction?.let { action ->
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    action()
                }
            }.also { callback ->
                (dialog as? OnBackPressedDispatcherOwner)?.onBackPressedDispatcher?.addCallback(
                    viewLifecycleOwner,
                    callback
                )
            }
        }
    }

    @Suppress("Unused")
    @Composable
    private fun ModalBottomSheet(content: @Composable () -> Unit) {
        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
            confirmValueChange = { modalBottomSheetValue ->
                when {
                    !isShown && modalBottomSheetValue == ModalBottomSheetValue.Expanded -> {
                        isShown = true
                        bottomSheetConfirmValueChangeCallback(modalBottomSheetValue)
                    }

                    !isShown -> true
                    else -> bottomSheetConfirmValueChangeCallback(modalBottomSheetValue)
                }
            })

        WeatherModalBottomSheet(
            sheetState = sheetState,
            sheetCornersStyle = sheetCornersStyle,
            onDismiss = { dismissWithoutAnimation() },
            hideBottomSheetTrigger = { dismissChannel.receive() },
            isFullscreen = isFullscreen,
            content = content,
        )
    }

    @Composable
    protected abstract fun Render(state: S)

    protected open fun handleEffect(effect: E) {
        // Override if you need something
    }

    private fun dismissWithoutAnimation() {
        val activity = activity
        if (activity != null && !activity.isFinishing) {
            super.dismiss()
        }
    }
}
