package com.developersbreach.kotlindictionarymultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform