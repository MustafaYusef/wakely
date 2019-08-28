package com.mustafayusef.wakely.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AuthViewModelFactory(val repostary: AuthRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repostary) as T
    }
}