package com.thoriqr.commercestorefront.core.common.navigation

import com.thoriqr.commercestorefront.ui.listing.ProductListingType

object BannerUrlParser {
    fun parse(
        url: String
    ): BannerNavigation? {

        val parts =
            url.trim('/')
                .split('/')

        if (parts.size != 2) {
            return null
        }

        val type =
            when (parts[0]) {
                "collection" ->
                    ProductListingType.COLLECTION

                "category" ->
                    ProductListingType.CATEGORY

                else ->
                    return null
            }

        return BannerNavigation(
            type = type,
            value = parts[1]
        )
    }
}