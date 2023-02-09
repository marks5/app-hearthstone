package com.example.hearthstoneapp

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.hearthstoneapp.domain.InfoHelper
import com.example.hearthstoneapp.presentation.ui.components.intent
import kotlin.random.Random

//@Composable
//fun CustomLazyRow(
//    list: List<String>,
//    keyValue: String,
//    context: Context,
//    color: Color,
//    font: FontFamily
//
//) {
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        content = {
//            item {
//                list.map {
////                    CustomHorizontalCard()
//                }
//            }
//        }
//    )
//}

@Composable
fun CustomHorizontalCard(
    text: String,
    font: FontFamily,
    modifierText: Modifier,
    colorCard: Color,
    colorText: Color,
    fontSizeText: TextUnit,
    textAlign: TextAlign,
    shapeSize: RoundedCornerShape,
    modifierColumn: Modifier,
    modifierCard: Modifier
) {
    Card(
        modifier = modifierCard,
        shape = shapeSize,
        backgroundColor = colorCard
    ) {
        Column(
            modifierColumn
        ) {

            Text(
                text = text,
                textAlign = textAlign,
                color = colorText,
                fontSize = fontSizeText,
                modifier = modifierText,
                fontFamily = font
            )

        }
    }
}

fun randomColor() = Color(
    Random.nextInt(255),
    Random.nextInt(255),
    Random.nextInt(255)
)
