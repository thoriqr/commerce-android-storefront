package com.thoriqr.commercestorefront.core.common

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {

    // Safe for all Android versions and avoids the deprecation warning
    private val indonesianLocale: Locale = Locale.Builder()
        .setLanguage("id")
        .setRegion("ID")
        .build()

    private val formatter = NumberFormat.getCurrencyInstance(indonesianLocale).apply {
        maximumFractionDigits = 0
        minimumFractionDigits = 0
    }

    fun format(value: Int): String {
        // .replace cleanups any weird hidden spacing anomalies in older Android system locales
        return formatter.format(value).replace(" ", "").replace("\u00A0", "")
    }
}