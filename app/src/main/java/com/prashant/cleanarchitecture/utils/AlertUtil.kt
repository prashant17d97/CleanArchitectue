package com.prashant.cleanarchitecture.utils

import com.prashant.cleanarchitecture.R
import com.prashant.cleanarchitecture.utils.appstate.AlertType
import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwner
import com.prashant.cleanarchitecture.utils.appstate.UIAlertModel

object AlertUtil {

    fun AppStatesOwner.loadProgress(
        show: Boolean,
        messageString: String? = null,
        message: Int = R.string.loading,
    ) {
        showAlert(
            UIAlertModel(
                alertType = AlertType.ProgressLoader(
                    messageString = messageString,
                    message = message,
                    verticalLayout = false
                ),
                show = show
            )
        )

    }

    fun AppStatesOwner.showSnackBar(
        message: String,
    ) {
        showAlert(
            UIAlertModel(
                alertType = AlertType.SnackBar(
                    message = message,
                ),
                show = true
            )
        )
    }


    fun AppStatesOwner.showSnackBar(
        alertType: AlertType.SnackBar,
    ) {
        showAlert(
            UIAlertModel(
                alertType = alertType,
                show = true
            )
        )
    }


    fun AppStatesOwner.showAlert(
        alertType: AlertType,
    ) {
        showAlert(
            UIAlertModel(
                alertType = alertType,
                show = true
            )
        )
    }


    fun AppStatesOwner.showAlertDialog(
        alertType: AlertType.AlertDialog,
    ) {
        showAlert(
            UIAlertModel(
                alertType = alertType,
                show = true
            )
        )
    }

    fun AppStatesOwner.toast(message: String) {
        showAlert(
            UIAlertModel(
                alertType = AlertType.Toast(message),
                show = true
            )
        )
    }

    fun AppStatesOwner.dismiss() {
        dismissAlert()
    }
}