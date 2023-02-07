package com.example.hearthstoneapp.presentation.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hearthstoneapp.R


@Composable
fun CustomFabButton(
    backgroundColor: Int,
    drawable: Int,
    intentClass: Class<*>,
    context: Context
) {
    FloatingActionButton(
        onClick = { intent(context, intentClass) },
        Modifier
            .padding(top = 64.dp, start = 24.dp)
            .size(80.dp),
        backgroundColor = colorResource(id = backgroundColor),
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(id = R.string.accessibility_fab),
        )
    }
}

fun intent(mContext: Context, intentClass: Class<*>) {
    mContext.startActivity(Intent(mContext, intentClass))
}