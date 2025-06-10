package com.developersbreach.kotlindictionarymultiplatform.di

import org.koin.core.KoinApplication

internal fun KoinApplication.appModules() {
    modules(
        httpClientModule,
        apiModule,
        repositoryModule,
        viewModelModule,
    )
}