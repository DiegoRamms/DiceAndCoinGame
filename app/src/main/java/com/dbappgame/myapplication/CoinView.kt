package com.dbappgame.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CoinView(viewModel: MainViewModel) {

    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = viewModel.coinImage.value),
            contentDescription = "Roller",
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null

                ) {
                    viewModel.onEvent(GameEvent.ThrowCoin)
                }
                .height(180.dp)
                .width(180.dp)

        )
    }


}