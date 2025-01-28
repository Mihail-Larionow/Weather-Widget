package com.michel.weather.presentation.composables.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme
import com.michel.weather.presentation.composables.WeatherScreenDefaults

@Composable
internal fun WeatherList(
    items: List<Int>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        items.forEachIndexed { index, item ->
            WeatherListItem(
                item = item,
            )

            if (index < items.lastIndex) {
                Divider(
                    color = WeatherTheme.colors.backgroundPlaceholder,
                    modifier = Modifier.padding(
                        start = WeatherScreenDefaults.HorizontalPadding,
                        end = WeatherScreenDefaults.HorizontalPadding
                    )
                )
            }
        }
    }
}

@Composable
private fun WeatherListItem(
    item: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = item.toString(),
            style = WeatherTheme.typography.title2,
            color = WeatherTheme.colors.textPrimary,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@ThemePreviews
@Composable
private fun WeatherListPreview() = WeatherTheme {
    WeatherList(
        items = listOf(30, 22, 28),
        modifier = Modifier.background(
            WeatherTheme.colors.backgroundSecondary,
        )
    )
}
