package com.mustafayusef.wakely.ui.auth.Delegate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.wakely.ui.auth.AuthRepostary


class RegShopsViewModelFactory(val repostary: AuthRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegesterShopsViewModel(repostary) as T
    }
}