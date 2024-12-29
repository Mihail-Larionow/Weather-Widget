package com.michel.utils.extensions.view

import android.os.Parcelable
import androidx.fragment.app.Fragment

const val ARGS_KEY = "args"

private val noArgsException = Exception(
    "Fragment arguments are not provided. Pass valid arguments for fragment with SafeArgs"
)

private val invalidArgsException = IllegalArgumentException(
    "Fragment arguments are invalid or have invalid id. " +
            "Make sure you pass arguments of valid class and id of the arguments is \"$ARGS_KEY\""
)

fun <ARGS : Parcelable> Fragment.getArgs(consumeArguments: Boolean = false): ARGS =
    (arguments?.takeIf { args -> !args.isEmpty } ?: throw noArgsException)
        .getParcelable<ARGS>(ARGS_KEY)
        ?.also {
            if (consumeArguments) {
                arguments = null
            }
        } ?: throw invalidArgsException

fun <ARGS : Parcelable> Fragment.getArgsLazy(consumeArguments: Boolean = false): Lazy<ARGS> = lazy {
    getArgs(consumeArguments)
}

fun <ARGS : Parcelable> Fragment.getNullableArgs(consumeArguments: Boolean = false): ARGS? {
    return (arguments?.takeIf { args -> !args.isEmpty } ?: return null)
        .getParcelable<ARGS>(ARGS_KEY)
        ?.also {
            if (consumeArguments) {
                arguments = null
            }
        }
}

fun <ARGS : Parcelable> Fragment.getNullableArgsLazy(consumeArguments: Boolean = false): Lazy<ARGS?> = lazy {
    getNullableArgs(consumeArguments)
}
