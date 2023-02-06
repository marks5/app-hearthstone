package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val numberOfDots = 7
val dotSize = 50.dp
val dotColor: Color = randomColor()
const val delayUnit = 200
const val duration = numberOfDots * delayUnit
val spaceBetween = 2.dp

@Composable
fun DotsPulsing() {

    @Composable
    fun Dot(scale: Float) {
        Spacer(
            Modifier
                .size(dotSize)
                .scale(scale)
                .background(
                    color = dotColor,
                    shape = CircleShape
                )
        )
    }

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun animateScaleWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(animation = keyframes {
            durationMillis = delayUnit * numberOfDots
            0f at delay with LinearEasing
            1f at delay + delayUnit with LinearEasing
            0f at delay + delay
        }, repeatMode = RepeatMode.Reverse),
    )

    val scales = arrayListOf<State<Float>>()
    for (i in 0 until numberOfDots) {
        scales.add(animateScaleWithDelay(delay = i * delayUnit))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        scales.forEach {
            Dot(it.value)
            Spacer(Modifier.width(spaceBetween))
        }
    }
}