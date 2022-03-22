package co.meimtiaz.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.meimtiaz.cache.dao.AppScheduleDao
import com.meimtiaz.domain.localentity.AppScheduleEntity

@Database(entities = [AppScheduleEntity::class],version = 1,exportSchema = false)
abstract class AppScheduleDatabase : RoomDatabase() {
    abstract fun appScheduleDao(): AppScheduleDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppScheduleDatabase? = null

        fun getInstance(context: Context): AppScheduleDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppScheduleDatabase::class.java, "appschedule.db")
                .build()
    }
}