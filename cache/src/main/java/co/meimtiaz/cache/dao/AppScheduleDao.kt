package co.meimtiaz.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meimtiaz.domain.localentity.AppScheduleEntity

@Dao
interface AppScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppSchedule(appSchedule: AppScheduleEntity)

    @Query("SELECT * FROM appschedules ORDER BY id DESC")
    fun getAllAppSchedules(): LiveData<List<AppScheduleEntity>>

    @Query("DELETE FROM appschedules")
    suspend fun deleteAllAppSchedules()

    @Query("DELETE FROM appschedules WHERE id =:id")
    suspend fun deleteSingleAppSchedule(id: Int)
}