package com.michel.profile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileState

@Composable
internal fun ProfileScreenContent(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        OptionButtons(
            onIntent = onIntent,
            modifier = Modifier.align(Alignment.Center),
        )

        BackButton(
            onClick = { onIntent(ProfileIntent.BackClicked) },
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .background(Color.Green)
            .clickable { onClick() }
    )
}

@Composable
private fun OptionButtons(
    onIntent: (ProfileIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Настройки",
            modifier = Modifier.clickable(
                onClick = { onIntent(ProfileIntent.SettingsClicked) }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "О приложении",
            modifier = Modifier.clickable(
                onClick = { onIntent(ProfileIntent.AppInfoClicked) }
            )
        )
    }
}

internal val MockProfileState = ProfileState.Loaded(
    name = "Имя"
)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        state = MockProfileState,
        onIntent = { },
    )
}
