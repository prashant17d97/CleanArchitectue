package com.prashant.cleanarchitecture

import androidx.lifecycle.ViewModel
import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwner
import org.koin.core.component.KoinComponent

class MainViewModel(
    private val appStatesOwner: AppStatesOwner
) : ViewModel(), KoinComponent {
    val alertEvent = appStatesOwner.alertEvent
    fun dismissAlert() {
        appStatesOwner.dismissAlert()
    }
}