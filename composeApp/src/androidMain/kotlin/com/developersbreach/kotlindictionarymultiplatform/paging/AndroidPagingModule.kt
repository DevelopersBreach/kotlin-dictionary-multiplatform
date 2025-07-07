package com.developersbreach.kotlindictionarymultiplatform.paging

import org.koin.dsl.module

val androidPagingModule = module {
    single<TopicPager> { createTopicPager() }
}