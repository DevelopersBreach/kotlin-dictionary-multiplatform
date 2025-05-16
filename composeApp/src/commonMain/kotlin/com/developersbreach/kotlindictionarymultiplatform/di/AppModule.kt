package com.developersbreach.kotlindictionarymultiplatform.di

import org.koin.core.module.dsl.viewModel
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.DetailViewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        DetailViewModel(get())
    }
}
