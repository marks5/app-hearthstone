package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(text: String, colorBackground: Int, fontFamily: Int) {
    Text(
        text,
        color = colorResource(id = colorBackground),
        fontSize = 22.sp,
        modifier = Modifier.padding(
            start = 280.dp,
            bottom = 8.dp,
        ),
        fontFamily = FontFamily(Font(fontFamily))
    )
}
