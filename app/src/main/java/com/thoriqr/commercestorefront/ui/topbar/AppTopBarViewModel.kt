package com.thoriqr.commercestorefront.ui.topbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppTopBarViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(AppTopBarUiState())

    val uiState =
        _uiState.asStateFlow()

    fun updateSearchQuery(
        query: String
    ) {
        _uiState.update {
            it.copy(
                submittedQuery = query
            )
        }
    }

    fun clearSearchQuery() {
        _uiState.update {
            it.copy(
                submittedQuery = ""
            )
        }
    }
}