package com.mobyle.recyclerviewexoplayer.infra.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    fun launch(block: suspend () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        block()
    }
}