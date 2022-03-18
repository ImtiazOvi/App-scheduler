package co.meimtiaz.cache

import android.app.Application
import androidx.room.Room
import co.meimtiaz.cache.dao.AppScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppScheduleDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppScheduleDatabase {
        return Room.databaseBuilder(application, AppScheduleDatabase::class.java, "appschedule.db").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideAppScheduleDao(appScheduleDatabase: AppScheduleDatabase):
            AppScheduleDao = appScheduleDatabase.appScheduleDao()


}
