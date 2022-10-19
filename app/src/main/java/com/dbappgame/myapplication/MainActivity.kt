package com.dbappgame.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbappgame.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            MyApplicationTheme() {
                val colors = listOf(MaterialTheme.colors.primary, Color.Black)
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colors.primary
                )


                Surface() {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .backgroundGradient(colors), horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Column {
                           displayRadioGroup(viewModel)
                        }

                        Box(modifier = Modifier.weight(80f)) {
                            if (viewModel.gameSelectedState.value == Game.DICE) RollerDiceView(viewModel)
                            else CoinView(viewModel)
                        }

                        Text(text = viewModel.textResult.value, color = Color.White, fontSize = 20.sp, modifier = Modifier.weight(20f))
                    }
                }
                

            }
        }
    }
}


@Composable
fun displayRadioGroup(viewModel: MainViewModel){

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = viewModel.gameSelectedState.value == Game.DICE, onClick = { viewModel.onEvent(GameEvent.OnSelectGame(Game.DICE)) })
        Text(
            text = "Dice",
            modifier = Modifier
                .clickable(onClick = { viewModel.onEvent(GameEvent.OnSelectGame(Game.DICE)) })
                .padding(start = 4.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.size(4.dp))
        RadioButton(selected = viewModel.gameSelectedState.value == Game.COIN, onClick = {viewModel.onEvent(GameEvent.OnSelectGame(Game.COIN)) })
        Text(
            text = "Coin",
            modifier = Modifier
                .clickable(onClick = { viewModel.onEvent(GameEvent.OnSelectGame(Game.COIN)) })
                .padding(start = 4.dp),
            color = Color.White
        )
    }
}


fun Modifier.backgroundGradient(colors: List<Color>): Modifier{
    val gradient = Brush.verticalGradient(
        colors = colors
    )

    return this.background(gradient)
}
