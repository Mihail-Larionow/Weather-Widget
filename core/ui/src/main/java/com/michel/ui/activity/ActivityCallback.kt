package com.michel.ui.activity

import android.app.Activity

interface ActivityCallback {

    fun onCreate(activity: Activity) {}

    fun onDestroy(activity: Activity) {}
}
