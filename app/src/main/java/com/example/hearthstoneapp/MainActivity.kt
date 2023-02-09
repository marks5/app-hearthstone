package com.example.hearthstoneapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hearthstoneapp.domain.InfoHelper
import com.example.hearthstoneapp.presentation.UiState
import com.example.hearthstoneapp.presentation.ui.components.intent
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HearthStoneAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.cultured)
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun MainScreen(viewModel: MainViewModel = get()) {
    viewModel.getInfo()

    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is UiState.Success -> {

            val info = (state.value as UiState.Success).value

            info.classes?.let {

                viewModel.setInfoResult(info)

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        contentAlignment = Alignment.TopStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 90.dp, start = 24.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.title_home),
                                color = colorResource(id = R.color.dark_gunmetal),
                                fontSize = 40.sp,
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontFamily = FontFamily(Font(R.font.avenir_900))
                            )
                            Divider(startIndent = 8.dp, thickness = 1.dp, color = Color.White)

                            Box {
                                val context = LocalContext.current
                                LazyColumn(
                                    contentPadding = PaddingValues(
                                        horizontal = 8.dp,
                                        vertical = 8.dp
                                    ),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    content = {

                                        viewModel.getCardInfoList().forEach { (key, values) ->
                                            item {
                                                Text(
                                                    modifier = Modifier
                                                        .padding(
                                                            top = 24.dp
                                                        ),
                                                    color = Color(R.color.dark_silver),
                                                    fontSize = 16.sp,
                                                    text = key,
                                                    fontFamily = FontFamily(Font(R.font.avenir_400))
                                                )
                                            }

                                            item {
                                                LazyRow(
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                    content = {
                                                        item {
                                                            values.map {
                                                                CustomHorizontalCard(
                                                                    text = it,
                                                                    font = FontFamily(Font(R.font.avenir_400)),
                                                                    modifierText = Modifier.padding(
                                                                        4.dp
                                                                    ),
                                                                    colorCard = randomColor(),
                                                                    colorText = colorResource(id = R.color.white),
                                                                    fontSizeText = 18.sp,
                                                                    textAlign = TextAlign.Left,
                                                                    shapeSize = RoundedCornerShape(
                                                                        16.dp
                                                                    ),
                                                                    modifierColumn = Modifier.padding(
                                                                        top = 50.dp,
                                                                        start = 10.dp
                                                                    ),
                                                                    modifierCard = Modifier
                                                                        .padding(
                                                                            start = 16.dp,
                                                                            top = 10.dp
                                                                        )
                                                                        .width(144.dp)
                                                                        .height(104.dp)
                                                                        .clickable {
                                                                            goToDetailsActivity(
                                                                                context,
                                                                                it,
                                                                                key
                                                                            )
                                                                        }
                                                                )
                                                            }
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        is UiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        else -> {}
    }
}

private fun goToDetailsActivity(
    context: Context,
    value: String,
    key: String
) {
    intent(
        mContext = context,
        intentClass = DetailsActivity::class.java
    )
    InfoHelper
        .getInstance()
        .setItemClicked(value)
    InfoHelper
        .getInstance()
        .setItemKeyClicked(
            key
        )
}