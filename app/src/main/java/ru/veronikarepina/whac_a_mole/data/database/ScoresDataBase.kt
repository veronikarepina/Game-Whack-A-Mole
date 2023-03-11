package ru.veronikarepina.whac_a_mole.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.veronikarepina.whac_a_mole.model.Scores

@Database(entities = [Scores::class], version = 2, exportSchema = false)
abstract class ScoresDataBase: RoomDatabase() {
    abstract fun scoresDao(): Dao
    companion object{
        private var INSTANCE: ScoresDataBase? = null
        fun getDataBase(context: Context): ScoresDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScoresDataBase::class.java,
                    "ScoresDataBase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}