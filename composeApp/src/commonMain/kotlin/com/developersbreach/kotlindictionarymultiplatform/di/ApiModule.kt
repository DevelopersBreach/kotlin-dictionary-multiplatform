package com.developersbreach.kotlindictionarymultiplatform.di

import com.developersbreach.kotlindictionarymultiplatform.core.network.api.GeminiApiService
import org.koin.dsl.module

internal val apiModule = module {
    single {
        GeminiApiService(client = get(), json = get())
    }
}