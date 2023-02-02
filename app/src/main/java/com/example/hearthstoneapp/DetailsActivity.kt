package com.example.hearthstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.InfoHelper
import com.example.hearthstoneapp.domain.model.CardByFilterEntity
import com.example.hearthstoneapp.presentation.ui.components.CustomFabButton
import com.example.hearthstoneapp.presentation.ui.components.CustomText
import com.example.hearthstoneapp.presentation.ui.components.customVerticalList
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
                    color = colorResource(id = R.color.cultured)
                ) {
                    MainScreen()
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

    Column {
        CustomFabButton(
            backgroundColor = R.color.dark_gunmetal,
            intentClass = MainActivity::class.java,
            context = LocalContext.current,
            drawable = R.drawable.ic_button_back
        )
        CustomText(
            text = InfoHelper.getInstance().itemKeySelected,
            colorBackground = R.color.dark_gunmetal,
            fontFamily = R.font.avenir_book,
            fontSizeText = 22.sp,
            modifier = Modifier.padding(
                start = 280.dp,
                bottom = 8.dp,
            )
        )
        customVerticalList(emptyList())
    }
}
