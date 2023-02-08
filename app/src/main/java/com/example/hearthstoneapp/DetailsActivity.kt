package com.example.hearthstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.InfoHelper
import com.example.hearthstoneapp.presentation.ui.components.CustomFabButton
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.DetailsUiState
import com.example.hearthstoneapp.presentation.viewmodel.DetailsViewModel
//import com.example.hearthstoneapp.presentation.viewmodel.InfoUiState
import org.koin.androidx.compose.get

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HearthStoneAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.cultured)
                ) {
                    DetailsScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DetailsScreen(viewModel: DetailsViewModel = get()) {
    viewModel.setItemName(FilterName = InfoHelper.getInstance().getItemKeyClicked(),
        itemName = InfoHelper.getInstance().getItemClicked())
//    DotsPulsing()
    val state = viewModel.uiStateSuccess.collectAsState()

    when (state.value) {
        is DetailsUiState.Success -> {
            val cardListRaceResult = (state.value as DetailsUiState.Success).cardsByFilter

            if (cardListRaceResult.isNotEmpty()) {
                Column {
                    val context = LocalContext.current

                    FloatingActionButton(
                        onClick = {
                            (context as DetailsActivity).finish()
                        },
                        Modifier
                            .padding(top = 64.dp, start = 24.dp)
                            .size(80.dp),
                        backgroundColor = colorResource(id = R.color.dark_gunmetal),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_button_back),
                            contentDescription = stringResource(id = R.string.accessibility_fab),
                        )
                    }

                    Text(
                        text = InfoHelper.getInstance().itemKeySelected,
                        color = colorResource(id = R.color.dark_gunmetal),
                        fontSize = 22.sp,
                        modifier = Modifier.padding(
                            start = 280.dp,
                            bottom = 8.dp,
                        ),
                        fontFamily = FontFamily(Font(R.font.avenir_400))
                    )

                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2)
                    ) {
                        cardListRaceResult.map {
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
            } else {
                Column {
                    CustomFabButton(
                        backgroundColor = R.color.dark_gunmetal,
                        intentClass = MainActivity::class.java,
                        context = LocalContext.current,
                        drawable = R.drawable.ic_button_back
                    )

                    Text(
                        text = "Nothing to show",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .width(100.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        is DetailsUiState.Loading -> {
//            DotsPulsing()
        }
    }
}
