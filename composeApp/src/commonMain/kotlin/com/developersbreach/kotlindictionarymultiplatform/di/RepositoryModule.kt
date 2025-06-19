package com.developersbreach.kotlindictionarymultiplatform.di

import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single {
        DetailRepository(get())
    }
    single {
        TopicRepository(get())
    }
}