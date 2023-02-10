package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CustomFabButton(
    onClickFun: () -> Unit,
    modifierFab: Modifier,
    backgroundFabColor: Color,
    painterImage: Painter,
    contentDescriptionImage: String
) {
    FloatingActionButton(
        onClick = { onClickFun },
        modifier = modifierFab,
        backgroundColor = backgroundFabColor,
    ) {
        Image(
            painter = painterImage,
            contentDescription = contentDescriptionImage,
        )
    }
}