package com.michel.mvi.view

interface StoreInputContract<Intent> {
    fun accept(intent: Intent)
}
