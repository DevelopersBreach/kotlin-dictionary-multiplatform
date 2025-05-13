package com.developersbreach.kotlindictionarymultiplatform

const val API_KEY =""

suspend fun details() {
    val details = KtorHttpClient.generateTopicDetails("variables", API_KEY)
    println(details)
}