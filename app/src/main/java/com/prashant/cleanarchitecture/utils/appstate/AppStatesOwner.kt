package com.prashant.cleanarchitecture.utils.appstate

import kotlinx.coroutines.flow.SharedFlow

interface AppStatesOwner {

    val alertEvent: SharedFlow<UIAlertModel>

    fun showAlert(uiAlertModel: UIAlertModel)

    fun dismissAlert()


}