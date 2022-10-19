package com.dbappgame.myapplication


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private var _gameSelectedState = mutableStateOf(Game.DICE)
    val gameSelectedState: State<Game> get() = _gameSelectedState

    private val _diceImage = mutableStateOf(R.drawable.dice_one)
    val diceImage: State<Int> get() = _diceImage

    private val _coinImage = mutableStateOf(R.drawable.head)
    val coinImage: State<Int> get() = _coinImage

    private val _textResult = mutableStateOf("")
    val textResult: State<String> get() = _textResult



    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.OnSelectGame -> {
                _gameSelectedState.value = event.game
            }
            is GameEvent.RollDice -> {
                rollDice()
            }
            is GameEvent.ThrowCoin -> {
                throwCoin()
            }
        }
    }



    private fun rollDice(){
        viewModelScope.launch {
            LIST_IMAGES.forEach {
                _diceImage.value = it
                delay(100)
            }
            val randomNumber = Random.nextInt(1, 7)
            val diceImage = when (randomNumber) {
                1 -> R.drawable.dice_one
                2 -> R.drawable.dice_two
                3 -> R.drawable.dice_three
                4 -> R.drawable.dice_four
                5 -> R.drawable.dice_five
                else -> R.drawable.dice_six
            }

            _textResult.value = "You got $randomNumber"
            _diceImage.value = diceImage
        }
    }

    private fun throwCoin(){
        viewModelScope.launch {
            for( i in 1..10) {
                _coinImage.value = if(i % 2== 0) R.drawable.head else R.drawable.tail
                delay(100)
            }
            val randomNumber = Random.nextInt(1, 3)
            val coinImage = when (randomNumber) {
                1 -> R.drawable.head
                else -> R.drawable.tail
            }
            _textResult.value = "You got ${if (randomNumber == 1) "Head" else "Tail"}"
            _coinImage.value = coinImage
        }
    }

}


sealed class GameEvent {
    data class OnSelectGame(val game: Game) : GameEvent()
    object RollDice : GameEvent()
    object ThrowCoin: GameEvent()
}

enum class Game {
    DICE,
    COIN
}
