package com.michel.designsystem.composables.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface AvatarState {

    val avatarUrl: String?
    val size: Dp

    @Immutable
    data class Big(
        override val avatarUrl: String?,
        override val size: Dp = 64.dp,
    ) : AvatarState

    @Immutable
    data class Small(
        override val avatarUrl: String?,
        override val size: Dp = 32.dp,
    ) : AvatarState

}
