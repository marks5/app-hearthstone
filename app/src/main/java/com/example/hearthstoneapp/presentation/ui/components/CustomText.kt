package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    colorBackground: Int,
    fontFamily: Int,
    fontSizeText: TextUnit = 18.sp,
    textAlign: TextAlign? = null,
    modifier: Modifier
) {
    Text(
        text = text,
        color = colorResource(id = colorBackground),
        fontSize = fontSizeText,
        modifier = modifier,
        fontFamily = FontFamily(Font(fontFamily))
    )
}
