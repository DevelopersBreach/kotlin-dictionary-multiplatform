package com.developersbreach.kotlindictionarymultiplatform.paging

data class Page<T>(
    val items: List<T>,
    val nextCursor: Any? = null,
)