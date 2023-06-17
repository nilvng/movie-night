package com.nillin.movienight.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class ContextViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
}