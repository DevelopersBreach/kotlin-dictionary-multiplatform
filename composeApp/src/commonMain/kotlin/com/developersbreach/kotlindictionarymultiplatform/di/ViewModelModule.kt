package com.developersbreach.kotlindictionarymultiplatform.di

import androidx.lifecycle.SavedStateHandle
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailViewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { (handle: SavedStateHandle) ->
        DetailViewModel(
            savedStateHandle = handle,
            repository = get(),
        )
    }

    viewModel {
        TopicViewModel(get())
    }
}