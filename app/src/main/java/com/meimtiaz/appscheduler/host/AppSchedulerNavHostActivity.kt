package com.meimtiaz.appscheduler.host

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.meimtiaz.appscheduler.databinding.ActivityAppSchedulerNavHostBinding
import com.meimtiaz.common.base.BaseActivity
import com.meimtiaz.common.extfun.IntentKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppSchedulerNavHostActivity: BaseActivity<ActivityAppSchedulerNavHostBinding>() {
    override fun viewBindingLayout(): ActivityAppSchedulerNavHostBinding = ActivityAppSchedulerNavHostBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        val finalHost =
            NavHostFragment.create(
                com.meimtiaz.navigation.R.navigation.nav_graph,
                bundleOf("screen_name" to (intent?.extras?.getString(IntentKey.screenName)?:""))
            )
        supportFragmentManager.beginTransaction()
            .replace(binding.screenContainer.id, finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()
    }
}