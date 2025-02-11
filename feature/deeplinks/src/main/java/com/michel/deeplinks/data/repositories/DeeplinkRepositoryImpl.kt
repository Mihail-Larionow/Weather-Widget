package com.michel.deeplinks.data.repositories

import androidx.core.net.toUri
import com.michel.deeplinks.domain.models.Deeplink
import com.michel.deeplinks.domain.models.DeeplinkAction
import com.michel.deeplinks.domain.models.Screen
import com.michel.deeplinks.domain.repositories.DeeplinkRepository
import javax.inject.Inject

class DeeplinkRepositoryImpl @Inject constructor() : DeeplinkRepository {

    override fun parse(deeplink: Deeplink): Result<DeeplinkAction> {
        val deeplinkAction = deeplink.url.parse()

        return if (deeplinkAction != null) {
            Result.success(deeplinkAction)
        } else {
            Result.failure(Exception())
        }
    }

    private fun String.parse(): DeeplinkAction? {
        val parsedUrl = try {
            this.toUri()
        } catch (_: Exception) {
            return null
        }

        return when (parsedUrl.host) {
            APP_INFORMATION_HOST -> Screen.AppInfo
            PROFILE_HOST -> Screen.Profile
            else -> null
        }
    }

    companion object {
        private const val PROFILE_HOST = "profile"
        private const val APP_INFORMATION_HOST = "app_information"
    }
}
