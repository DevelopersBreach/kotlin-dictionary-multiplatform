package com.developersbreach.kotlindictionarymultiplatform.di

import org.koin.dsl.module
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailViewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicViewModel
import org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel {
        DetailViewModel(get())
    }
    viewModel {
        TopicViewModel()
    }
}