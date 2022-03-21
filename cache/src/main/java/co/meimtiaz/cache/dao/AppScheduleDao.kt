package co.meimtiaz.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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
    suspend fun deleteAppScheduleById(id: Int)

    @Update
    suspend fun updateAppSchedule(appScheduleEntity: AppScheduleEntity)

    @Query("UPDATE appschedules SET isAppStarted = :isAppStarted WHERE packageName = :packageName")
    fun updateAppStartStatus(packageName: String, isAppStarted: Boolean?)
}