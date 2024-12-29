package com.michel.weatherit.main

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.michel.ui.activity.ActivityCallback
import com.michel.ui.activity.ActivityRegistry
import com.michel.utils.ProcessUtils
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class ActivityRegistryImpl(application: Application) : ActivityRegistry {

    private val activities: MutableList<WeakReference<Activity>> = mutableListOf()
    private val callbacks: MutableList<ActivityCallback> = mutableListOf()

    init {
        if (ProcessUtils.isMainApplicationProcess(application)) {
            registerActivityCallbacks(application)
        }
    }

    override fun get(): Activity? = activities
        .lastNotNullOfOrNull { reference -> reference.get() }

    @Suppress("UNCHECKED_CAST")
    override fun <TYPE : Any> getOfType(clazz: KClass<TYPE>): TYPE? = activities
        .lastOrNull { reference -> clazz.isInstance(reference.get()) }
        ?.get() as? TYPE

    override fun registerActivityCallback(callback: ActivityCallback) {
        callbacks.add(callback)
    }

    override fun removeActivityCallback(callback: ActivityCallback) {
        callbacks.remove(callback)
    }

    private fun registerActivityCallbacks(application: Application) {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                    activities.add(WeakReference(activity))
                    for (callback in callbacks) {
                        callback.onCreate(activity)
                    }
                }

                override fun onActivityDestroyed(activity: Activity) {
                    for (callback in callbacks) {
                        callback.onDestroy(activity)
                    }
                    activities.removeAll { reference -> reference.get() == activity || reference.get() == null }
                }

                override fun onActivityResumed(p0: Activity) {
                    // Nothing to do here
                }

                override fun onActivityPaused(p0: Activity) {
                    // Nothing to do here
                }

                override fun onActivityStarted(p0: Activity) {
                    // Nothing to do here
                }

                override fun onActivityStopped(p0: Activity) {
                    // Nothing to do here
                }

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                    // Nothing to do here
                }
            }
        )
    }

    inline fun <TYPE, RESULT : Any> Iterable<TYPE>.lastNotNullOfOrNull(
        transform: (TYPE) -> RESULT?,
    ): RESULT? {
        for (index in count() - 1 downTo 0) {
            transform(elementAt(index))?.run { return this }
        }
        return null
    }
}
