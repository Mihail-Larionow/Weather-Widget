package com.michel.profile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.extensions.isScrolled
import com.michel.designsystem.theme.WeatherTheme
import com.michel.profile.presentation.composables.header.Header
import com.michel.profile.presentation.composables.menu.Menu
import com.michel.profile.presentation.composables.menu.MenuItemType
import com.michel.profile.presentation.model.ProfileHeader
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileState

private val DefaultCornerSize = 32.dp

@Composable
internal fun ProfileScreen(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val isListScrolled = remember { derivedStateOf { scrollState.isScrolled() } }

    Scaffold(
        backgroundColor = WeatherTheme.colors.backgroundPrimary,
        topBar = {
            ProfileToolbar(
                isListScrolled = isListScrolled,
                onBackClick = { onIntent(ProfileIntent.BackClicked) },
            )
        },
        content = { innerPadding ->
            ProfileContent(
                state = state,
                onIntent = onIntent,
                scrollState = scrollState,
                paddingValues = innerPadding,
            )
        }
    )
}

@Composable
internal fun ProfileContent(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
) {
    when (state) {
        is ProfileState.Loading -> {}
        is ProfileState.Loaded -> {
            LoadedContent(
                state = state,
                onIntent = onIntent,
                scrollState = scrollState,
                paddingValues = paddingValues,
            )
        }
    }
}

@Composable
private fun LoadedContent(
    state: ProfileState.Loaded,
    onIntent: (ProfileIntent) -> Unit,
    scrollState: LazyListState,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier.padding(paddingValues),
    ) {
        item {
            Header(
                header = state.header,
                onIntent = onIntent,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = WeatherTheme.colors.backgroundSecondary,
                        shape = RoundedCornerShape(
                            bottomStart = DefaultCornerSize,
                            bottomEnd = DefaultCornerSize,
                        ),
                    )
            )
        }
        item {
            Menu(
                items = MenuItemType.entries,
                onClick = { onIntent(ProfileIntent.MenuItemClicked(it)) },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(
                        color = WeatherTheme.colors.backgroundSecondary,
                        shape = RoundedCornerShape(DefaultCornerSize),
                    )
                    .padding(top = 20.dp, bottom = 20.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() = WeatherTheme {
    ProfileScreen(
        state = ProfileState.Loaded(
            header = ProfileHeader.Authorized,
        ),
        onIntent = { },
    )
}


