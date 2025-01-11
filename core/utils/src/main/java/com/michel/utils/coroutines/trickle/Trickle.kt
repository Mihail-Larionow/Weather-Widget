package com.michel.utils.coroutines.trickle

import kotlinx.coroutines.flow.Flow

interface Trickle<T> {
    fun toFlow(): Flow<T>
}
