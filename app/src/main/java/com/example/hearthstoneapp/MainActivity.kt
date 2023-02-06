package com.example.hearthstoneapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.InfoHelper
import com.example.hearthstoneapp.presentation.ui.components.CustomText
import com.example.hearthstoneapp.presentation.ui.components.CustomVerticalList
import com.example.hearthstoneapp.presentation.ui.components.DotsPulsing
import com.example.hearthstoneapp.presentation.ui.components.goTo
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.CharViewModel
import com.example.hearthstoneapp.presentation.viewmodel.InfoUiState
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
                    DetailsScreen()
                }
            }
        }
    }
}

@Composable
private fun CustomDivider() {
    CustomText(
        text = stringResource(id = R.string.title_home),
        colorBackground = R.color.dark_gunmetal,
        fontFamily = R.font.avenir_black,
        fontSizeText = 40.sp,
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
        color = colorResource(
            id = R.color.white
        )
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun DetailsScreen(viewModel: CharViewModel = get()) {
    viewModel.getInfo()
//    DotsPulsing()
    val state = viewModel.uiStateSuccess.collectAsState(initial = InfoUiState.Loading(true))

    //Gerenciar o estado do loading
    when (state.value) {
        is InfoUiState.Success -> {

            val infoResult = (state.value as InfoUiState.Success).info
            if (infoResult?.classes != null) {
                (state.value as InfoUiState.Success).info?.let { viewModel.setInfoResult(it) }
                Column {
                    CustomDivider()
                    CustomHorizontalList(viewModel.getCardInfoList())
                }
            }
        }
        is InfoUiState.Loading -> {
//            DotsPulsing()
        }
        else -> {}
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun CustomHorizontalList(
    classes: Map<String, List<String>>
) {
    CustomVerticalList(classes)
}

fun goToDetails(mContext: Context, itemSelected: String, key: String) {
    goTo(mContext, DetailsActivity::class.java)
    InfoHelper.getInstance().setItemClicked(itemSelected)
    InfoHelper.getInstance().setItemKeyClicked(key)
}