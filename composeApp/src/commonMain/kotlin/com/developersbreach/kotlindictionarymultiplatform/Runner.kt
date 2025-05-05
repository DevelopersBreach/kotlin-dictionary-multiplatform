package com.developersbreach.kotlindictionarymultiplatform

const val API_KEY =""

suspend fun main() {
    val details = KtorHttpClient.generateTopicDetails("variables", API_KEY)
    println(details)
}