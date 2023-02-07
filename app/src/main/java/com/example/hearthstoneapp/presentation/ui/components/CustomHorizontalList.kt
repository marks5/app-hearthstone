package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
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
fun CustomVerticalList(listResult: List<CardByFilterEntity>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        listResult.map {
            it.img?.let {
                item {
                    Card(
                        modifier = Modifier
                            .height(223.dp)
                            .fillMaxSize()
                            .padding(
                                top = 8.dp,
                                end = 8.dp
                            ),
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(it),
                            contentDescription = stringResource(id = R.string.accessibility_item_image),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }
    }
}