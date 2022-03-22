package com.meimtiaz.appscheduler.di

import com.meimtiaz.data.AppScheduleRepoImpl
import com.meimtiaz.domain.repository.AppScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppScheduleRepository(appScheduleRepoImpl: AppScheduleRepoImpl): AppScheduleRepository
}