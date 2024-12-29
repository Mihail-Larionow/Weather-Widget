package com.michel.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Process
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader

object ProcessUtils {

    private const val ROBOLECTRIC_FINGERPRINT = "robolectric"

    fun isMainApplicationProcess(context: Context): Boolean {
        val isCurrentProcessNameMatches = getCurrentProcessName(context) == context.applicationInfo.processName
        val isRobolectricRun = Build.FINGERPRINT == ROBOLECTRIC_FINGERPRINT
        return isCurrentProcessNameMatches || isRobolectricRun
    }

    private fun getCurrentProcessName(context: Context): String? {
        val pid = Process.myPid()
        return readFromProc(pid) ?: readFromActivityManager(pid, context)
    }

    private fun readFromProc(pid: Int): String? {
        var reader: Reader? = null
        return try {
            var c: Int
            reader = BufferedReader(InputStreamReader(FileInputStream(File("/proc/$pid/cmdline")), "iso-8859-1"))
            val result = StringBuilder()
            while (reader.read().also { c = it } > 0) {
                result.append(c.toChar())
            }
            result.toString()
        } catch (e: IOException) {
            null
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    // Nothing to do, all right!
                }
            }
        }
    }

    private fun readFromActivityManager(pid: Int, context: Context): String? {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == pid) {
                return processInfo.processName
            }
        }
        return null
    }
}
