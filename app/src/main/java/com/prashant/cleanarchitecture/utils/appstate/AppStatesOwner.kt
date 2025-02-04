package com.prashant.cleanarchitecture.utils.appstate

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface AppStatesOwner {

    val bottomBarVisibility: StateFlow<Boolean>

    val alertEvent: SharedFlow<UIAlertModel>

    fun showAlert(uiAlertModel: UIAlertModel)

    fun dismissAlert()

    fun showBottomBar()

    fun hideBottomBar()

}