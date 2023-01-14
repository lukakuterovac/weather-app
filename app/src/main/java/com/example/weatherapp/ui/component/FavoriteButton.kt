package com.example.weatherapp.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.spacing

const val OPACITY_FACTOR = 0.5F

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Crossfade(
        targetState = isFavorite,
        animationSpec = tween(durationMillis = 500)
    ) {
        Image(
            painter = painterResource(
                id = if (it) R.drawable.ic_heart_filled else R.drawable.ic_heart_empty
            ),
            contentDescription = null,
            modifier = modifier
                .size(dimensionResource(id = R.dimen.favorite_button_size))
                .clickable { onClick() }
                .background(
                    color = Color.Gray.copy(alpha = OPACITY_FACTOR),
                    shape = CircleShape
                )
                .padding(MaterialTheme.spacing.small),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun FavoriteButtonPreview() {
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    FavoriteButton(
        isFavorite = isFavorite,
        onClick = { isFavorite = !isFavorite }
    )
}
