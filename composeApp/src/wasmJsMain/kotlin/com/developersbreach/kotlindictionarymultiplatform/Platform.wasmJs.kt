package com.developersbreach.kotlindictionarymultiplatform

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform {
    return WasmPlatform()
}

actual fun getOpenApiKey(): String {
    return ""
}