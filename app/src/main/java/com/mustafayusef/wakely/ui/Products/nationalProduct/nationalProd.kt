package com.mustafayusef.wakely.ui.Products.nationalProduct

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.nationalProduct.National
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.Products.ProductRepostary
import com.mustafayusef.wakely.ui.Products.ProductViewModelFactory
import com.mustafayusef.wakely.ui.Products.ProductesViewModel
import com.mustafayusef.wakely.ui.Products.productLesener
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.progress.*


class nationalProd : Fragment(),NationalLesener {
    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: National) {
        progLoading?.visibility=View.GONE
        ordersList?.layoutManager=LinearLayoutManager(context)
        ordersList?.adapter=NationalProductsAdapter(context!!,response.data)
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return   inflater.inflate(R.layout.orders_fragment, container, false)
        }

 var viewModel:ProductesViewModel?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= ProductRepostary(api!!)
        val factory= ProductViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(ProductesViewModel::class.java)
        viewModel?.AuthNational=this
        viewModel?.national()
    }
}
