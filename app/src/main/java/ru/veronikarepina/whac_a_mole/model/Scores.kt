package ru.veronikarepina.whac_a_mole.model

import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "scores_table")
data class Scores(
    @PrimaryKey
    val score: Int
): Serializable
