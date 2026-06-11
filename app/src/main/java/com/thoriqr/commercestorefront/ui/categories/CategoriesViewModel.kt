package com.thoriqr.commercestorefront.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.util.onError
import com.thoriqr.commercestorefront.core.common.util.onSuccess
import com.thoriqr.commercestorefront.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            categoryRepository.getCategoryTree()
                .onSuccess { categories ->

                    _uiState.update {
                        it.copy(
                            categories = categories,
                            isLoading = false
                        )
                    }
                }
                .onError { message ->

                    _uiState.update {
                        it.copy(
                            error = message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun retry() {
        loadCategories()
    }
}