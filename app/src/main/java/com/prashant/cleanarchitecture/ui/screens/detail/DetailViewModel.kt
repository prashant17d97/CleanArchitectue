package com.prashant.cleanarchitecture.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetHeroesByIdUseCase
import com.prashant.cleanarchitecture.utils.AlertUtil.dismiss
import com.prashant.cleanarchitecture.utils.AlertUtil.loadProgress
import com.prashant.cleanarchitecture.utils.AlertUtil.showSnackBar
import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val appStatesOwner: AppStatesOwner,
    private val getHeroesByIdUseCase: GetHeroesByIdUseCase
) : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun retrieve(id: String) {
        appStatesOwner.hideBottomBar()
        if (id == product.value?.id.toString()) return
        viewModelScope.launch {
            getHeroesByIdUseCase(id).parseResponse(
                loading = {
                    appStatesOwner.loadProgress(
                        show = true,
                    )
                    if (!it) {
                        appStatesOwner.dismiss()
                    }
                },
                success = { product, message, _ ->
                    _product.value = product
                    message?.let {
                        appStatesOwner.showSnackBar(
                            message = it,
                        )
                    }
                },
                error = { message, _ ->
                    message?.let {
                        appStatesOwner.showSnackBar(
                            message = it,
                        )
                    }
                }
            )
        }
    }
}