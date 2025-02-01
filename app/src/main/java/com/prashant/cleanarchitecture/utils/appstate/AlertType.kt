package com.prashant.cleanarchitecture.utils.appstate


import androidx.compose.material3.SnackbarDuration
import com.prashant.cleanarchitecture.R

sealed class AlertType() {
    data class SnackBar(
        val message: String = "",
        val actionLabel: String? = null,
        val snackBarColor: SnackBarColor = SnackBarColor.Info,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val withDismissAction: Boolean = false,
        val action: (() -> Unit)? = null,
        val onDismiss: (() -> Unit)? = null,
    ) : AlertType()

    data class AlertDialog(
        val titleResource: Int = R.string.app_name,
        val title: String? = null,
        val description: String? = null,
        val descriptionResource: Int = R.string.empty,
        val dismissOnBackPress: Boolean = false,
        val dismissOnClickOutside: Boolean = false,
        val confirmButtonTextResource: Int = R.string.empty,
        val dismissButtonTextResource: Int = R.string.cancel_btn,
        val showWarningColorButton: Boolean = false,
        val confirmButton: () -> Unit = {},
        val dismissButton: () -> Unit = {}
    ) : AlertType()

    data class ProgressLoader(
        val messageString: String? = null,
        val message: Int = R.string.empty,
        val percentage: Int? = null,
        val verticalLayout: Boolean = true
    ) : AlertType()

    data class Toast(val message: String) : AlertType()


    data object Nothing : AlertType()
}


enum class SnackBarColor {
    Info,
    Success,
    Warning,
    Error
}