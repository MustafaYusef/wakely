package com.mustafayusef.wakely.ui.orders.companyOrders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class OrdersCompanyViewModelFactory(val repostary: OrdersCompanyRepostary) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrdersCompanyViewModel(repostary) as T
    }
}