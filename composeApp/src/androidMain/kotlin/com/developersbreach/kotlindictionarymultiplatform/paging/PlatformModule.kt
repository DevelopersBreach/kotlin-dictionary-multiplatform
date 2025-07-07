package com.developersbreach.kotlindictionarymultiplatform.paging

import org.koin.core.KoinApplication

fun KoinApplication.androidPlatformModules(): KoinApplication {
    return modules(
        androidPagingModule,
    )
}