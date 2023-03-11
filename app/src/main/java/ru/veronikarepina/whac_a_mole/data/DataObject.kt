package ru.veronikarepina.whac_a_mole.data

import android.content.Context
import ru.veronikarepina.whac_a_mole.data.database.ScoresDataBase
import ru.veronikarepina.whac_a_mole.data.repository.RepositoryDao

object DataObject {
    lateinit var dataBase: ScoresDataBase
    lateinit var repository: RepositoryDao
    fun initData(context: Context){
        dataBase = ScoresDataBase.getDataBase(context)
        repository = RepositoryDao(dataBase.scoresDao())
    }
}