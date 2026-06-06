package com.thoriqr.commercestorefront.core.common.model

import kotlinx.serialization.Serializable

@Serializable
data class CursorMetaDto(
    val hasMore: Boolean,
    val nextCursor: String?
)
