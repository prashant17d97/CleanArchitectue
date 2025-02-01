package com.prashant.cleanarchitecture.utils.appstate

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class AppStatesOwnerImpl : AppStatesOwner {

    private val _alertEvent: MutableSharedFlow<UIAlertModel> = MutableSharedFlow(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val alertEvent: SharedFlow<UIAlertModel>
        get() = _alertEvent

    override fun showAlert(uiAlertModel: UIAlertModel) {
        _alertEvent.tryEmit(uiAlertModel)
    }

    override fun dismissAlert() {
        _alertEvent.tryEmit(UIAlertModel())
    }

}