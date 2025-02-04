package com.prashant.cleanarchitecture.utils.appstate

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class AppStatesOwnerImpl : AppStatesOwner {

    private val _alertEvent: MutableSharedFlow<UIAlertModel> = MutableSharedFlow(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val alertEvent: SharedFlow<UIAlertModel>
        get() = _alertEvent

    private val _bottomBarVisibility: MutableStateFlow<Boolean> = MutableStateFlow(true)
    override val bottomBarVisibility: StateFlow<Boolean>
        get() = _bottomBarVisibility

    override fun showAlert(uiAlertModel: UIAlertModel) {
        _alertEvent.tryEmit(uiAlertModel)
    }

    override fun dismissAlert() {
        _alertEvent.tryEmit(UIAlertModel())
    }

    override fun showBottomBar() {
        _bottomBarVisibility.value = true
    }

    override fun hideBottomBar() {
        _bottomBarVisibility.value = false
    }

}