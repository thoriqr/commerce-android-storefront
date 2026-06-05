package com.thoriqr.commercestorefront.core.common.util

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController

object KeyboardUtils {

    fun hide(
        focusManager: FocusManager,
        keyboardController: SoftwareKeyboardController?
    ) {
        focusManager.clearFocus(force = true)
        keyboardController?.hide()
    }
}