package com.michel.profile.presentation.composables.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.michel.designsystem.theme.WeatherTheme
import com.michel.profile.presentation.model.ProfileHeader
import com.michel.profile.presentation.mvi.entities.ProfileIntent

@Composable
internal fun Header(
    header: ProfileHeader,
    onIntent: (ProfileIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(vertical = 6.dp),
    ) {
        when (header) {
            is ProfileHeader.UnAuthorized -> UnAuthorizedContent()
            is ProfileHeader.Authorized -> AuthorizedContent(
                header = header,
                onIntent = onIntent,
            )
        }
    }
}

@Composable
private fun UnAuthorizedContent() {

}

@Composable
private fun AuthorizedContent(
    header: ProfileHeader.Authorized,
    onIntent: (ProfileIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(WeatherTheme.colors.textPrimary)
    )
}
