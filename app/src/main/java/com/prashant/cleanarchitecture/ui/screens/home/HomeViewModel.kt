package com.prashant.cleanarchitecture.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetProductsUseCase
import com.prashant.cleanarchitecture.utils.AlertUtil.dismiss
import com.prashant.cleanarchitecture.utils.AlertUtil.loadProgress
import com.prashant.cleanarchitecture.utils.AlertUtil.showSnackBar
import com.prashant.cleanarchitecture.utils.AlertUtil.toast
import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appStatesOwner: AppStatesOwner,
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private val _product: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val products: StateFlow<List<Product>> = _product

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        fetchSuperHeroes()
    }

    fun fetchSuperHeroes(refreshRequest: Boolean = false) {
        if (refreshRequest) {
            _isRefreshing.value = true
        }
        viewModelScope.launch {
            appStatesOwner.loadProgress(
                show = true,
            )
            getProductsUseCase(Unit).parseResponse(
                loading = {
                    appStatesOwner.loadProgress(
                        show = true,
                    )
                    if (!it) {
                        appStatesOwner.dismiss()
                    }
                    _isRefreshing.value = it

                },
                success = { products, responseMessage, _ ->
                    responseMessage?.let {
                        appStatesOwner.showSnackBar(
                            it,
                        )
                    }
                    if (products != null) {
                        _product.value = products.products
                    }
                },
                error = { message, _ ->
                    message?.let { appStatesOwner.toast(it) }
                }
            )
        }

    }
}