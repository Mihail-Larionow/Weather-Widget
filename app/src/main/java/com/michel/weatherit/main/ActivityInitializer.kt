package com.michel.weatherit.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ActivityInitializer @AssistedInject constructor(
    @Assisted private val activity: MainActivity,
) {
    private val lifecycleScope: CoroutineScope
        get() = activity.lifecycleScope

    fun init() {
        initNotifications()
    }

    private fun initNotifications() {
        lifecycleScope.launch {
            requestNotificationPermissionIfNotGranted()
        }
    }

    private fun requestNotificationPermissionIfNotGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationsPermission()) {
            lifecycleScope.launch {
                requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasNotificationsPermission(): Boolean =
        ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED

    private suspend fun requestPermission(permission: String) {

    }

    @AssistedFactory
    interface Factory {

        fun create(activity: MainActivity): ActivityInitializer
    }
}
