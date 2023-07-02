package com.islamzada.cryptotrackerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel(), CoroutineScope {

    private val job = Job()

    // Define the coroutine context for the view model
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // Called when the view model is no longer in use
    override fun onCleared() {
        super.onCleared()
        // Cancel the job to clean up any ongoing coroutines
        job.cancel()
    }
}
