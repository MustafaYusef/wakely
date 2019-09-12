package com.mustafayusef.wakely.ui.Products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ProductViewModelFactory(val repostary: ProductRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductesViewModel(repostary) as T
    }
}