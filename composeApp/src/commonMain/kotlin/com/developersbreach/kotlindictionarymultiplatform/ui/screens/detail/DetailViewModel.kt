package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppDestinations
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailViewModel.ItemTableOfContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository,
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<AppDestinations.Detail>().topicId

    private val _state = MutableStateFlow<UiState<KotlinTopicDetails>>(UiState.Loading)
    val state: StateFlow<UiState<KotlinTopicDetails>> = _state

    init {
        viewModelScope.launch {
            try {
                fetchTopic()
            } catch (e: IOException) {
                Log.e("DetailViewModel", "Error fetching topic: ${e.message}", e)
            }
        }
    }

    private suspend fun fetchTopic() {
        _state.value = repository.fetchTopic(topicId).fold(
            ifLeft = { UiState.Error(it) },
            ifRight = { UiState.Success(it) },
        )
    }



//    data class ItemTableOfContent(
//        val index :  Int,
//        val isVisible : Boolean,
//    )

    sealed class ItemTableOfContent{
        // abstract val index : Int
        data class Introduction(val intro : String):ItemTableOfContent()
        data object Syntax: ItemTableOfContent()
        data class Section (
            val index : Int
        ):ItemTableOfContent()
        data object Pitfall: ItemTableOfContent()
        data object RelatedTopic: ItemTableOfContent()
    }
}

fun list(topic: KotlinTopicDetails): List<ItemTableOfContent> {
    val tocItems = buildList {
        //add(stringResource(Res.string.introduction_bullet) to index++)
        add(ItemTableOfContent.Introduction(intro = topic.intro))
        if (topic.syntax.signature.isNotBlank()) add(ItemTableOfContent.Syntax)
        topic.sections.forEachIndexed {index,  section ->
            section.heading?.let {
                add(ItemTableOfContent.Section(index))
            }
        }
        if (topic.pitfalls.isNotEmpty()) add(ItemTableOfContent.Pitfall)
        if (topic.relatedTopics.isNotEmpty()) add(ItemTableOfContent.RelatedTopic)
    }
    return tocItems
}