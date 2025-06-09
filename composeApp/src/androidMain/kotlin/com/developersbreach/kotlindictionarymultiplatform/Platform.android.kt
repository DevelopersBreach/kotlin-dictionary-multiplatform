package com.developersbreach.kotlindictionarymultiplatform

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}

actual fun getOpenApiKey(): String {
    return BuildConfig.OPEN_API_KEY
}