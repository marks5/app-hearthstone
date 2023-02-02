package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.hearthstoneapp.R
import com.example.hearthstoneapp.domain.model.CardByFilterEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun customVerticalList(listResult: List<CardByFilterEntity>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {

        listResult.map {
            item {
                Image(
                    painter = rememberAsyncImagePainter(it.img),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 8.dp,
                            end = 8.dp
                        ),
                    contentDescription = stringResource(id = R.string.accessibility_item_image),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}