package com.example.hearthstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.InfoHelper
import com.example.hearthstoneapp.presentation.ui.components.CustomFabButton
import com.example.hearthstoneapp.presentation.ui.components.CustomText
import com.example.hearthstoneapp.presentation.ui.components.CustomVerticalList
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.DetailsUiState
import com.example.hearthstoneapp.presentation.viewmodel.DetailsViewModel
import com.example.hearthstoneapp.presentation.viewmodel.InfoUiState
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

@Composable
private fun DetailsScreen(viewModel: DetailsViewModel = get()) {
    viewModel.getFilterName()
//    DotsPulsing()
    val state = viewModel.uiStateSuccess.collectAsState(initial = InfoUiState.Loading(true))

    //Gerenciar o estado do loading
    when (state.value) {
        is DetailsUiState.Success -> {
            val charListRaceResult = (state.value as DetailsUiState.Success).cardsByFilter
            if (charListRaceResult.isNotEmpty()) {
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
                    CustomVerticalList(charListRaceResult)
                }
            }
        }

        is DetailsUiState.Loading -> {
//            DotsPulsing()
        }
    }
}
