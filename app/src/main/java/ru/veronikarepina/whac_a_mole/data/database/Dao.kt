package ru.veronikarepina.whac_a_mole.data.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.veronikarepina.whac_a_mole.model.Scores

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Scores)

    @Query("SELECT MAX(score) FROM scores_table")
    fun getHighScore(): LiveData<Int>
}