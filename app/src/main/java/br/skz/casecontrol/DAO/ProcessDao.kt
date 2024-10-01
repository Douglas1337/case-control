package br.skz.casecontrol.DAO

import androidx.room.*
import br.skz.casecontrol.classes.ProcessEntity

@Dao
interface ProcessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(process: ProcessEntity)

    @Update
    suspend fun update(process: ProcessEntity)

    @Delete
    suspend fun delete(process: ProcessEntity)

    @Query("SELECT * FROM process_table")
    suspend fun getAllProcesses(): List<ProcessEntity>
}
