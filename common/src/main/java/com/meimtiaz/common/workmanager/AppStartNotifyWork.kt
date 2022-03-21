package com.meimtiaz.common.workmanager

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class AppStartNotifyWork(private var context: Context, params: WorkerParameters) : Worker(context, params) {

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

    }

    companion object {
        const val APP_SCHEDULE_PACKAGE_NAME = "APP_SCHEDULE_PACKAGE_NAME"
    }
}