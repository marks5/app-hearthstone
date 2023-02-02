package com.example.hearthstoneapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hearthstoneapp.R

@Composable
fun CustomVerticalList(mapList: Map<String, Any> = emptyMap()) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight(),
        content = {
            mapList.forEach { (key, values) ->
                item {
                    CustomText(
                        modifier = Modifier
                            .padding(
                                top = 34.dp,
                                start = 39.dp,
                            ),
                        colorBackground = R.color.dark_silver,
                        fontSizeText = 24.sp,
                        text = key,
                        fontFamily = R.font.avenir_book
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            item {
                                CustomHorizontalCard(values as List<Any>, key, LocalContext.current)
                            }
                        }
                    )
                }
            }
        }
    )
}