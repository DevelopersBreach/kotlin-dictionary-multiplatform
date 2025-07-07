package com.developersbreach.kotlindictionarymultiplatform.paging

data class Page<T>(
    val items: List<T>,
    val isEndReached: Boolean,
)