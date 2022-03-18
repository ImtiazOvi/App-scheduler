package co.meimtiaz.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import co.meimtiaz.cache.dao.AppScheduleDao
import com.meimtiaz.domain.AppScheduleEntity

@Database(entities = [AppScheduleEntity::class],version = 1,exportSchema = false)
abstract class AppScheduleDatabase : RoomDatabase() {
    abstract fun appScheduleDao(): AppScheduleDao
}