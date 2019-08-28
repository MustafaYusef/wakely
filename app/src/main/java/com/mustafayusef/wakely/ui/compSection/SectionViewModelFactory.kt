package com.mustafayusef.wakely.ui.compSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SectionViewModelFactory(val repostary: SecRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SectionsViewModel(repostary) as T
    }
}