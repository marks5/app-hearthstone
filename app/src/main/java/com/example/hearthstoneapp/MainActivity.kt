package com.example.hearthstoneapp

import android.content.Context
import android.content.Intent
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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.InfoHelper
import com.example.hearthstoneapp.presentation.ui.theme.HearthStoneAppTheme
import com.example.hearthstoneapp.presentation.viewmodel.CharViewModel
import com.example.hearthstoneapp.presentation.viewmodel.InfoUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import kotlin.random.Random

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
                    InfoHelper.getInstance().setInfoResultValue()
                    MainScreen()
                    CustomHomeView()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomHomeView() {
    Column {
        CustomDivider()
        CustomHorizontalList(
            InfoHelper.getInstance().getInfoListObjects() as HashMap<String, ArrayList<String>>
        )
    }
}

@Composable
private fun CustomDivider() {
    Text(
        text = stringResource(id = R.string.title_home),
        color = colorResource(id = R.color.dark_gunmetal),
        fontSize = 40.sp,
        modifier = Modifier.padding(
            start = 37.dp,
            top = 90.dp,
            bottom = 8.dp,
            end = 113.dp
        ),
        fontFamily = FontFamily(Font(R.font.avenir_black))
    )
    Divider(
        startIndent = 39.dp,
        thickness = 0.5.dp,
        color = colorResource(
            id = R.color.white
        )
    )
}

@Composable
private fun MainScreen(viewModel: CharViewModel = get()) {
    viewModel.getInfo()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.uiStateSuccess.collectLatest { uiStateValue ->
                    setUiSate(uiStateValue)
                }
            }
        }
    }
}

private fun setUiSate(uiState: InfoUiState) {
    when (uiState) {
        is InfoUiState.Loading -> {
            uiState.isLoading
        }
        is InfoUiState.Success -> {
            uiState.info.apply {
//                InfoHelper.getInstance().setInfoResultValue(
//                    InfoFilterEntity(
//                        classes = this.classes,
//                        sets = this.sets,
//                        qualities = this.qualities,
//                        races = this.races,
//                        types = this.types,
//                        standard = this.standard,
//                        wild = this.wild,
//                        factions = this.factions,
//                        locales = this.locales
//                    )
//                )
            }
        }
        is InfoUiState.Error -> {
            uiState.error
        }
    }
}


@Composable
fun CircularProgressIndicatorSample(isLoading: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun CustomHorizontalList(
    classes: Map<String, List<String>>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight(),
        content = {
            classes.forEach { (key, values) ->
                item {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = 34.dp,
                                start = 39.dp,
                            ),
                        color = Color(R.color.dark_silver),
                        fontSize = 24.sp,
                        text = key,
                        fontFamily = FontFamily(Font(R.font.avenir_book))
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            item {
                                HorizontalCard(values, key)
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
    list: List<Any>,
    Key: String
) {
    val mContext = LocalContext.current

    list.map {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp)
                .width(144.dp)
                .height(104.dp)
                .clickable { goToDetails(mContext, it.toString(), Key) },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = randomColor()
        )
        {
            Column(
                Modifier.padding(top = 65.dp, start = 23.dp)
            ) {
                Text(
                    fontFamily = FontFamily(Font(R.font.avenir_book)),
                    text = it.toString(),
                    modifier = Modifier.padding(4.dp),
                    color = Color.White,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

fun goToDetails(mContext: Context, itemSelected: String, key: String) {
    mContext.startActivity(Intent(mContext, DetailsActivity::class.java))
    InfoHelper.getInstance().setItemClicked(itemSelected)
    InfoHelper.getInstance().setItemKeyClicked(key)
}

private fun randomColor() = Color(
    Random.nextInt(255),
    Random.nextInt(255),
    Random.nextInt(255)
)