package ru.veronikarepina.whac_a_mole.data.repository

import androidx.lifecycle.LiveData
import ru.veronikarepina.whac_a_mole.data.database.Dao
import ru.veronikarepina.whac_a_mole.model.Scores

class RepositoryDao(private val dao: Dao) {
    suspend fun insertScore(score: Scores){
        dao.insert(score)
    }
    fun getHighScore(): LiveData<Int>{
        return dao.getHighScore()
    }
}