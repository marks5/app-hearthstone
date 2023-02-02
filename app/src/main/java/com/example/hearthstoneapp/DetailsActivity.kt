package com.example.hearthstoneapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.InfoHelper
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.DetailsUiState
import com.example.hearthstoneapp.presentation.viewmodel.DetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HearthStoneAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(R.color.cultured)
                ) {
                    MainScreen()
                    CustomTitle(emptyList())
                }
            }
        }
    }
}

@Composable
private fun MainScreen(viewModel: DetailsViewModel = get()) {
    viewModel.getRaceList(InfoHelper.getInstance().itemSelected)

    var result: List<CardByFilterEntity>? = null
    var isLoading: Boolean? = false

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.uiStateSuccess.collectLatest { uiStateValue ->
                    when (uiStateValue) {
                        is DetailsUiState.Loading -> {
                            isLoading = true
                        }
                        is DetailsUiState.Success -> {
                            result = uiStateValue.charByRaceList
                            isLoading = false
                        }
                        is DetailsUiState.Error -> {
                            uiStateValue.error
                            isLoading = false
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTitle(charByRaceList: List<CardByFilterEntity>) {

    Column {
        CustomButton()
        Text(
            InfoHelper.getInstance().itemKeySelected,
            color = Color(R.color.dark_gunmetal),
            fontSize = 22.sp,
            modifier = Modifier.padding(
                start = 280.dp,
                bottom = 8.dp,
            )
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {

            val list = listOf(1..10)
//            charByRaceList.map {
//                item {
//                    Image(
//                        painter = rememberAsyncImagePainter(it.img),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(
//                                top = 8.dp,
//                                end = 8.dp
//                            ),
//                        contentScale = ContentScale.Fit
//                    )
//                }
//            }

            list.map {
                items(10) {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 8.dp
                            ),
                        contentScale = ContentScale.Fit,
                        contentDescription = stringResource(id = R.string.accessibility_item_image),
                    )
                }
            }
        }
    }
}

@Composable
fun CustomButton() {
    val mContext = LocalContext.current
    FloatingActionButton(
        onClick = { onBackPressed(mContext) },
        Modifier
            .padding(top = 64.dp, start = 24.dp)
            .size(80.dp),
        backgroundColor = Color(R.color.dark_gunmetal),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_button_back),
            contentDescription = stringResource(id = R.string.accessibility_fab),
        )
    }
}

fun onBackPressed(mContext: Context) {
    mContext.startActivity(Intent(mContext, MainActivity::class.java))
    InfoHelper.getInstance().setItemClicked("")
}
