package com.developersbreach.kotlindictionarymultiplatform.data.topic.utils

import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.FirestoreDocument
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

fun FirestoreDocument.toTopic(): Topic {
    return Topic(
        name = fields["name"]?.stringValue.orEmpty(),
        description = fields["description"]?.stringValue.orEmpty(),
    )
}