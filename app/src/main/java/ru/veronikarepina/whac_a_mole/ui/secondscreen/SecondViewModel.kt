package ru.veronikarepina.whac_a_mole.ui.secondscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.veronikarepina.whac_a_mole.data.DataObject
import ru.veronikarepina.whac_a_mole.model.Scores

class SecondViewModel: ViewModel() {
    private val repository = DataObject.repository
    lateinit var highScore: LiveData<Int>

    init {
        getHighScore()
    }

    private fun getHighScore() = viewModelScope.launch{
        highScore = repository.getHighScore()
    }

    fun insertScore(score: Scores) = viewModelScope.launch{
        repository.insertScore(score)
    }
}