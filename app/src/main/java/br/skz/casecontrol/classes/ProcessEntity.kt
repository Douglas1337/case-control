package br.skz.casecontrol.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "process_table")
data class ProcessEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: String,
    val party: String,
    val description: String,
    val status: String
)