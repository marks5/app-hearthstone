package com.example.hearthstoneapp.presentation.ui.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.goToDetails
import kotlin.random.Random

@Composable
fun CustomHorizontalCard(
    values: List<Any>,
    Key: String,
    context: Context
) {
    values.map {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp)
                .width(144.dp)
                .height(104.dp)
                .clickable { goToDetails(context, it.toString(), Key) },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = randomColor()
        ) {
            Column(
                Modifier.padding(top = 50.dp, start = 10.dp)
            ) {
                CustomText(
                    fontFamily = R.font.avenir_book,
                    text = it.toString(),
                    modifier = Modifier.padding(4.dp),
                    colorBackground = R.color.white,
                    textAlign = TextAlign.Left,
                    fontSizeText = 18.sp
                )
            }
        }
    }
}

private fun randomColor() = Color(
    Random.nextInt(255),
    Random.nextInt(255),
    Random.nextInt(255)
)