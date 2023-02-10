package com.example.hearthstoneapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
import com.example.hearthstoneapp.domain.InfoHelper
import com.example.hearthstoneapp.presentation.UiState
import com.example.hearthstoneapp.presentation.ui.components.CustomFabButton
import com.example.hearthstoneapp.presentation.ui.components.CustomVerticalCard
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.DetailsViewModel
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
    viewModel.setItemName(
        filterName = InfoHelper.getInstance().getItemKeyClicked(),
        itemName = InfoHelper.getInstance().getItemClicked()
    )

    val context = LocalContext.current
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is UiState.Success -> {
            val cardListRaceResult = (state.value as UiState.Success).value

            if (cardListRaceResult.isNotEmpty()) {
                Column {

                    CustomFabButton(
                        contextRef = (context as DetailsActivity),
                        modifierFab = Modifier
                            .padding(top = 64.dp, start = 24.dp)
                            .size(80.dp),
                        backgroundFabColor = colorResource(id = R.color.dark_gunmetal),
                        modifierImage = Modifier.size(45.dp),
                        painterImage = painterResource(id = R.drawable.ic_button_back),
                        contentDescriptionImage = stringResource(id = R.string.accessibility_fab)
                    )

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
                                    CustomVerticalCard(
                                        Modifier
                                            .fillMaxSize()
                                            .height(224.dp)
                                            .width(189.dp)
                                            .padding(8.dp),
                                        rememberAsyncImagePainter(it),
                                        stringResource(id = R.string.accessibility_item_image),
                                        ContentScale.Fit
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                CustomHeader(context, stringResource(id = R.string.placeholder_text))
            }
        }

        is UiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        is UiState.Error -> {
            CustomHeader(context, stringResource(id = R.string.placeholder_text))
        }
    }
}

@Composable
private fun CustomHeader(context: Context, headerText: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp, start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.TopStart,
    ) {

        CustomFabButton(
            contextRef = (context as DetailsActivity),
            modifierFab = Modifier
                .size(80.dp),
            backgroundFabColor = colorResource(id = R.color.dark_gunmetal),
            modifierImage = Modifier.size(45.dp),
            painterImage = painterResource(id = R.drawable.ic_button_back),
            contentDescriptionImage = stringResource(id = R.string.accessibility_fab)
        )

        Text(
            text = headerText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, top = 24.dp),
            textAlign = TextAlign.End,
            fontFamily = FontFamily(Font(R.font.avenir_400)),
            color = colorResource(id = R.color.dark_gunmetal),
            fontSize = 22.sp,
        )
    }
}