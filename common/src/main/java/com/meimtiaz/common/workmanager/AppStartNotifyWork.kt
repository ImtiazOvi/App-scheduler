package com.meimtiaz.common.workmanager

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.meimtiaz.domain.usecase.UpdateAppStartStatusUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppStartNotifyWork(private var context: Context, params: WorkerParameters) : Worker(context, params) {
    @Inject
    lateinit var updateAppStartStatusUseCase: UpdateAppStartStatusUseCase

    override fun doWork(): Result {
        startApp(inputData.getString(APP_SCHEDULE_PACKAGE_NAME)?:"")
        return success()
    }

    private fun startApp(packageName: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.flags = FLAG_ACTIVITY_NEW_TASK
        intent?.action = Intent.ACTION_MAIN;
        intent?.addCategory(Intent.CATEGORY_LAUNCHER)
        context.startActivity(intent)

        CoroutineScope(Dispatchers.IO).launch {
            updateAppStartStatusUseCase.execute( UpdateAppStartStatusUseCase.Params(isAppStarted = true,
                packageName = packageName)
            )
        }

    }

    companion object {
        const val APP_SCHEDULE_PACKAGE_NAME = "APP_SCHEDULE_PACKAGE_NAME"
    }
}