package com.mustafayusef.wakely.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class OrdersViewModelFactory(val repostary: OrdersRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrdersViewModel(repostary) as T
    }
}