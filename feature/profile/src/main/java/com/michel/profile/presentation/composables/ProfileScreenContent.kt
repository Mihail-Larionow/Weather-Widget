package com.michel.profile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.michel.profile.R

@Composable
internal fun ProfileScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red )
    ) {
        Text(
            text = stringResource(R.string.weather_app_feature_profile_navigation_label),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent()
}
