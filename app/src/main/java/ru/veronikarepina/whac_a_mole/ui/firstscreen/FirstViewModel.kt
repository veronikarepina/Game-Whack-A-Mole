package ru.veronikarepina.whac_a_mole.ui.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.veronikarepina.whac_a_mole.data.DataObject

class FirstViewModel: ViewModel() {
    private val repository = DataObject.repository
    lateinit var highScore: LiveData<Int>

    init {
        getHighScore()
    }

    private fun getHighScore() = viewModelScope.launch{
         highScore = repository.getHighScore()
    }
}