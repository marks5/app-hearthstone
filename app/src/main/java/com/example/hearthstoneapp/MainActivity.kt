package com.example.hearthstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.hearthstoneapp.presentation.viewmodel.CharViewModel
import com.example.hearthstoneapp.presentation.viewmodel.InfoStateUiState
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HearthStoneAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF8F8F8)
                ) {
                    CustomHomeView()
//                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun CustomHomeView(viewModel: CharViewModel = get()) {
    val classes: List<String> = listOf("Druid", "Mage", "Druid", "Mage", "Druid", "Mage", "Druid", "Mage")
    Column {
        CustomDivider()
        CustomHorizontalList(viewModel, classes)
    }
}

@Composable
private fun CustomDivider() {
    Text(
        "Hearthstone",
        color = Color(0xFF202032),
        fontSize = 40.sp,
        modifier = Modifier.padding(
            start = 37.dp,
            top = 90.dp,
            bottom = 8.dp,
            end = 113.dp
        )
    )
    Divider(
        startIndent = 39.dp,
        thickness = 0.5.dp,
        color = Color(0xFFFFFFFF)
    )
}

@Composable
    private fun MainScreen(viewModel: CharViewModel = get()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.uiStateSuccess.collectLatest { uiState ->
                    when (uiState) {
                        is InfoStateUiState.Success -> {
//                            uiState.info.sets
//                            uiState.info.qualities
//                            uiState.info.races
//                            uiState.info.standard
//                            uiState.info.types
//                            uiState.info.wild
//                            uiState.info.factions
//                            uiState.info.locales
                        }
                        is InfoStateUiState.Error -> {

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomHorizontalList(
    viewModel: CharViewModel,
    classes: List<String>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight(),
        content = {

            viewModel.getListInfoFilters().map { itemFilter ->
                item {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = 34.dp,
                                start = 39.dp,
                            ),
                        color = Color(0xFF707070),
                        fontSize = 24.sp,
                        text = itemFilter
                    )
                }

                item {

                    MainScreen()
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(classes) { itemClass ->
                                HorizontalCard(itemClass)
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun HorizontalCard(
    itemHorizontalCard: String
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 10.dp)
            .width(144.dp)
            .height(104.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = randomColor()
    )
    {
        Column(
            Modifier.padding(top = 65.dp, start = 23.dp)
        ) {
            Text(
                text = itemHorizontalCard,
                modifier = Modifier.padding(4.dp),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Left
            )
        }
    }
}

private fun randomColor() = Color(
    Random.nextInt(255),
    Random.nextInt(255),
    Random.nextInt(255)
)