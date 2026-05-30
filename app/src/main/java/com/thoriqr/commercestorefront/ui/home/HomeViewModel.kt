package com.thoriqr.commercestorefront.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.NetworkResult
import com.thoriqr.commercestorefront.data.model.BannerPlacement
import com.thoriqr.commercestorefront.data.repository.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bannerRepository: BannerRepository): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBanners()
    }

    private fun loadBanners(){

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = bannerRepository.getBanners(
                BannerPlacement.HOMEPAGE_HERO
            )

            when (result) {

                is NetworkResult.Success -> {
                    _uiState.update {
                        it.copy(
                            banners = result.data,
                            isLoading = false
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }

            }
        }
    }
}