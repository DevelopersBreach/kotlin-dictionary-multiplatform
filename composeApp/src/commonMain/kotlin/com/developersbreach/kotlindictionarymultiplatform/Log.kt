package com.developersbreach.kotlindictionarymultiplatform

import co.touchlab.kermit.Logger


object Log {
    fun d(tag: String, message: String) {
        Logger.d(message, tag = tag)
    }

    fun i(tag: String, message: String) {
        Logger.i(message, tag = tag)
    }

    fun w(tag: String, message: String) {
        Logger.w(message, tag = tag)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Logger.e(message, throwable, tag = tag)
    }
}