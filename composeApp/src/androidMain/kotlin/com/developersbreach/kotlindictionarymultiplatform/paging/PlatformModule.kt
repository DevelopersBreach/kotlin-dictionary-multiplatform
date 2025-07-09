package com.developersbreach.kotlindictionarymultiplatform.paging

import org.koin.dsl.module

val androidModule = module {
    single<TopicPager> { createTopicPager() }
}