package com.michel.profile.presentation.composables.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.extensions.clickableWithRipple
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun Menu(
    items: List<MenuItemType>,
    onClick: (MenuItemType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            MenuItem(
                title = item.getTitle(),
                onClick = {
                    onClick(item)
                },
            )
        }
    }
}

@Composable
internal fun MenuItem(
    title: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.clickableWithRipple(onClick = onClick)
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = WeatherTheme.typography.title1,
            color = WeatherTheme.colors.textPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
        )
    }
}

@ThemePreviews
@Composable
private fun MenuPreview() = WeatherTheme {
    Menu(
        items = MenuItemType.entries,
        onClick = {}
    )
}
