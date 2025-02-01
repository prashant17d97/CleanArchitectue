package com.prashant.cleanarchitecture.utils.appstate

data class UIAlertModel(
    val alertType: AlertType = AlertType.Nothing,
    val show: Boolean = true,
)
