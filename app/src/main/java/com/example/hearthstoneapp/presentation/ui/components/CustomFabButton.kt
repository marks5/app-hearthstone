package com.example.hearthstoneapp.presentation.ui.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CustomFabButton(
    contextRef: Activity,
    modifierFab: Modifier,
    backgroundFabColor: Color,
    painterImage: Painter,
    contentDescriptionImage: String
) {
    FloatingActionButton(
        onClick = { contextRef.finish() },
        modifier = modifierFab,
        backgroundColor = backgroundFabColor,
    ) {
        Image(
            painter = painterImage,
            contentDescription = contentDescriptionImage,
        )
    }
}