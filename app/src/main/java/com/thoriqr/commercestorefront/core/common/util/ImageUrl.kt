package com.thoriqr.commercestorefront.core.common.util

import com.thoriqr.commercestorefront.BuildConfig

object ImageUrl {
    fun fromKey(imageKey: String): String {
        return "${BuildConfig.ASSET_BASE_URL}/$imageKey"
    }
}