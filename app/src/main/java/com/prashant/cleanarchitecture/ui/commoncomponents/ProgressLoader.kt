package com.prashant.cleanarchitecture.ui.commoncomponents

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.prashant.cleanarchitecture.R
import com.prashant.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.prashant.cleanarchitecture.utils.UICommnetUtils.string
import com.prashant.cleanarchitecture.utils.appstate.AlertType

@Composable
fun ProgressLoader(
    modifier: Modifier = Modifier,
    alertType: AlertType.ProgressLoader,
    showProgress: Boolean = true,
    dismissAlert: () -> Unit
) {
    AnimatedVisibility(visible = showProgress) {
        Dialog(onDismissRequest = dismissAlert) {
            ProgressDialogContent(
                modifier = modifier,
                alertType = alertType
            )
        }
    }
}

@Composable
private fun ProgressDialogContent(
    modifier: Modifier = Modifier,
    alertType: AlertType.ProgressLoader
) {
    val verticalLayout = alertType.verticalLayout
    val contentModifier = modifier
//        .fillMaxSize()
        .background(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.large
        )
        .padding(16.dp)

    if (verticalLayout) {
        // Vertical layout for progress dialog
        Column(
            modifier = contentModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            ProgressIndicator(alertType)
            ProgressMessage(alertType)
        }
    } else {
        // Horizontal layout for progress dialog
        Row(
            modifier = contentModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            ProgressIndicator(alertType)
            ProgressMessage(alertType)
        }
    }
}

@Composable
private fun ProgressIndicator(alertType: AlertType.ProgressLoader) {
    val progress = alertType.percentage?.let { it / 100f } ?: 0f
    if (alertType.percentage == null) {
        CircularProgressIndicator(
            strokeCap = StrokeCap.Round
        )
        return
    }
    CircularProgressIndicator(
        progress = { progress / 1f },
        strokeCap = StrokeCap.Round
    )

}

@Composable
private fun ProgressMessage(alertType: AlertType.ProgressLoader) {
    Text(
        text = alertType.messageString ?: alertType.message.string(),
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(
    showBackground = true, showSystemUi = true,
)
@Composable
fun ProgressLoaderPreview(
    @PreviewParameter(ProgressLoaderPreviewParameterProvider::class) progressLoader: AlertType.ProgressLoader,
) {
    CleanArchitectureTheme {
        Scaffold {
            ProgressLoader(alertType = progressLoader) {}
        }
    }
}

class ProgressLoaderPreviewParameterProvider : PreviewParameterProvider<AlertType.ProgressLoader> {
    override val values: Sequence<AlertType.ProgressLoader> = sequenceOf(
        AlertType.ProgressLoader(
            message = R.string.loading,
            verticalLayout = false,
            percentage = null
        ),
        AlertType.ProgressLoader(
            message = R.string.loading,
            verticalLayout = true,
            percentage = null
        ),
        AlertType.ProgressLoader(
            message = R.string.loading,
            verticalLayout = true,
            percentage = 25
        ),
        AlertType.ProgressLoader(
            message = R.string.loading,
            verticalLayout = false,
            percentage = 80
        )

    )

}





