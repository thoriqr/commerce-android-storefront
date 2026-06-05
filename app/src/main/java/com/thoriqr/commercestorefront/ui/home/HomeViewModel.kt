package com.thoriqr.commercestorefront.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoriqr.commercestorefront.core.common.util.NetworkResult
import com.thoriqr.commercestorefront.core.common.util.onError
import com.thoriqr.commercestorefront.core.common.util.onSuccess
import com.thoriqr.commercestorefront.data.model.BannerDto
import com.thoriqr.commercestorefront.data.model.BannerPlacement
import com.thoriqr.commercestorefront.data.model.CollectionPreviewDto
import com.thoriqr.commercestorefront.data.model.PopularCategoryDto
import com.thoriqr.commercestorefront.data.repository.BannerRepository
import com.thoriqr.commercestorefront.data.repository.CategoryRepository
import com.thoriqr.commercestorefront.data.repository.CollectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bannerRepository: BannerRepository,
    private val categoryRepository: CategoryRepository,
    private val collectionRepository: CollectionRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData(){
        viewModelScope.launch {
            val bannersDeferred = async {
                bannerRepository.getBanners(
                    BannerPlacement.HOMEPAGE_HERO
                )
            }

            val categoriesDeferred = async {
                categoryRepository.getPopularCategories()
            }

            val collectionPreviewDeferred = async {
                collectionRepository.getCollectionPreview()
            }

            handleBannerResult(bannersDeferred.await())

            handlePopularCategoriesResult(categoriesDeferred.await())

            handleCollectionPreviewResult(collectionPreviewDeferred.await())
        }
    }

    private fun handleBannerResult(
        result: NetworkResult<List<BannerDto>>
    ) {
       result
           .onSuccess { banners ->
               _uiState.update {
                   it.copy(
                       banners = banners
                   )
               }
           }
           .onError { message ->
               _uiState.update {
                   it.copy(
                       bannerError = message
                   )
               }
           }
    }


    private fun handlePopularCategoriesResult(
        result: NetworkResult<List<PopularCategoryDto>>
    ) {
        result
            .onSuccess { categories ->
                _uiState.update {
                    it.copy(
                        popularCategories = categories
                    )
                }
            }
            .onError { message ->
                _uiState.update {
                    it.copy(
                        popularCategoriesError = message
                    )
                }
            }
    }

    private fun handleCollectionPreviewResult(
        result: NetworkResult<List<CollectionPreviewDto>>
    ) {
        result
            .onSuccess { collections ->
                _uiState.update {
                    it.copy(
                        collectionPreview = collections
                    )
                }
            }
            .onError { message ->
                _uiState.update {
                    it.copy(
                        collectionPreviewError = message
                    )
                }
            }
    }
}