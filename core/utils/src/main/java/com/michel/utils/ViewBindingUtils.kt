package com.michel.utils

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.michel.utils.extensions.findViewsByType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

object ViewBindingUtils {

    class FragmentViewBindingDelegate<T : ViewBinding>(
        val fragment: Fragment,
        val viewBindingFactory: (View) -> T,
    ) : ReadOnlyProperty<Fragment, T> {
        private var binding: T? = null

        init {
            fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
                val viewLifecycleOwnerLiveDataObserver =
                    Observer<LifecycleOwner?> {
                        val viewLifecycleOwner = it ?: return@Observer

                        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                            override fun onDestroy(owner: LifecycleOwner) {
                                super.onDestroy(owner)
                                clearAdapterReferences()
                                binding = null
                            }
                        })
                    }

                override fun onCreate(owner: LifecycleOwner) {
                    super.onCreate(owner)
                    fragment.viewLifecycleOwnerLiveData.observeForever(
                        viewLifecycleOwnerLiveDataObserver
                    )
                }

                override fun onDestroy(owner: LifecycleOwner) {
                    super.onDestroy(owner)
                    fragment.viewLifecycleOwnerLiveData.removeObserver(
                        viewLifecycleOwnerLiveDataObserver
                    )
                }
            })
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            val binding = binding
            if (binding != null) {
                return binding
            }

            val lifecycle = try {
                fragment.viewLifecycleOwner.lifecycle
            } catch (cause: IllegalStateException) {
                throw IllegalStateException(
                    "Cannot get viewLifecycleOwner. " +
                            "Current fragment state: ${fragment.lifecycle.currentState}",
                    cause,
                )
            }
            if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
                throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
            }

            return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
        }

        fun clearAdapterReferences() {
            val viewGroup = binding?.root as? ViewGroup ?: return
            val recyclerViews = viewGroup.findViewsByType(RecyclerView::class)
            for (recyclerView in recyclerViews) {
                recyclerView?.adapter = null
            }
        }
    }

    fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(this, viewBindingFactory)
}
