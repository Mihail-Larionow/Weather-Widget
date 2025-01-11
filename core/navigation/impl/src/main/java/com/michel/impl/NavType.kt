package com.michel.impl

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

internal fun parcelableType(
    type: KType,
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<Parcelable>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("UNCHECKED_CAST")
            bundle.getParcelable(key, (type.classifier as KClass<out Parcelable>).java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): Parcelable = json.decodeFromString(
        deserializer = serializer(type),
        string = value,
    ) as Parcelable

    override fun serializeAsValue(value: Parcelable): String = json.encodeToString(serializer(type), value)

    override fun put(bundle: Bundle, key: String, value: Parcelable) = bundle.putParcelable(key, value)
}
