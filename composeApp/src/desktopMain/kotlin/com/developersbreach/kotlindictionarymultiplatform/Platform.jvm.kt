package com.developersbreach.kotlindictionarymultiplatform

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform {
    return JVMPlatform()
}