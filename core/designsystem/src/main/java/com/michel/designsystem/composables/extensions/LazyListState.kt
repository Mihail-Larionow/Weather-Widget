package com.michel.designsystem.composables.extensions

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolled(): Boolean =
    firstVisibleItemScrollOffset > 0 || firstVisibleItemIndex > 0
