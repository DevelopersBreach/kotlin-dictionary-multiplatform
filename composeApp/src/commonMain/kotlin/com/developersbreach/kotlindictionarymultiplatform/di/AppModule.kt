package com.developersbreach.kotlindictionarymultiplatform.di

import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import org.koin.dsl.module
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailViewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicViewModel
import org.koin.core.module.dsl.viewModel
import androidx.lifecycle.SavedStateHandle
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository

val appModule = module {
    single { DetailRepository() }
    single { TopicRepository }

    viewModel { (handle: SavedStateHandle) ->
        DetailViewModel(handle, get())
    }

    viewModel {
        TopicViewModel(get())
    }
}