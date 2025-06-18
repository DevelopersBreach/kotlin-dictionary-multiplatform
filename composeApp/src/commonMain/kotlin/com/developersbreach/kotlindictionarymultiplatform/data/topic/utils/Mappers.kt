package com.developersbreach.kotlindictionarymultiplatform.data.topic.utils

import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.RawTopic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

fun RawTopic.toTopic(): Topic {
    return Topic(
        name = fields["name"]?.value.orEmpty(),
        description = fields["description"]?.value.orEmpty(),
    )
}