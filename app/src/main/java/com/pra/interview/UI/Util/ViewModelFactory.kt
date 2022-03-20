package com.pra.myapplication.UI.Util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pra.interview.UI.fragment.ListScreen.TitleViewModel

class ViewModelFactory (private val app: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TitleViewModel::class.java)) {
            return TitleViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
