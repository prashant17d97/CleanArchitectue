package com.prashant.cleanarchitecture.ui.commoncomponents


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.prashant.cleanarchitecture.R
import com.prashant.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.prashant.cleanarchitecture.utils.UICommnetUtils.string
import com.prashant.cleanarchitecture.utils.appstate.AlertType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(
    modifier: Modifier = Modifier,
    alertDialog: AlertType.AlertDialog,
    dismissAlert: () -> Unit
) {
    // A helper function to handle the shared dismiss logic
    val handleDismiss: (() -> Unit) -> Unit = {
        dismissAlert()
        alertDialog.dismissButton.invoke()
    }

    // Creating the AlertDialog
    AlertDialog(
        modifier = modifier,
        title = alertDialog.title?.let { { Text(it) } },
        text = alertDialog.description?.let { { Text(it) } },
        onDismissRequest = { handleDismiss(alertDialog.dismissButton) },
        confirmButton = {
            AlertButton(
                text = alertDialog.confirmButtonTextResource.string(),
                onClick = { handleDismiss(alertDialog.confirmButton) }
            )
        },
        dismissButton = {
            AlertButton(
                text = alertDialog.dismissButtonTextResource.string(),
                onClick = { handleDismiss(alertDialog.dismissButton) }
            )
        }
    )
}

@Composable
fun AlertButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    if (text.isNotEmpty()) {
        Button(onClick = onClick, modifier) {
            Text(text)
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewAlertDialog(
    @PreviewParameter(AlertDialogPreviewParameterProvider::class) alertDialog: AlertType.AlertDialog,
) {
    CleanArchitectureTheme {
        AlertDialog(alertDialog = alertDialog, dismissAlert = { })
    }
}

class AlertDialogPreviewParameterProvider : PreviewParameterProvider<AlertType.AlertDialog> {
    override val values: Sequence<AlertType.AlertDialog> = sequenceOf(
        AlertType.AlertDialog(
            title = "AlertDialog Title",
            description = "AlertDialog Description",
            confirmButtonTextResource = R.string.okay,
            dismissButtonTextResource = R.string.cancel_btn,
            confirmButton = { },
            dismissButton = {}
        ),
        AlertType.AlertDialog(
            title = "AlertDialog Title 2",
            description = "lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            confirmButtonTextResource = R.string.okay,
            dismissButtonTextResource = R.string.cancel_btn,
            confirmButton = { },
            dismissButton = {}
        )

    )

}


