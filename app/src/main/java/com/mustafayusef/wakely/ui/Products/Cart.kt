package com.mustafayusef.wakely.ui.Products

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.cartResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.ordersResponse
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.network.myApi
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.progress.*
import java.lang.Exception

class Cart : Fragment(),productLesener {


    override fun OnSuccessSend(message: productsResponse) {
        context?.toast(message.message)
        progLoading?.visibility=View.GONE
    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: productsResponse) {

    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun OnSuccessAdd(message: productsResponse) {
    }

    override fun OnSuccessGetCart(message: Cart) {
        cartItemsList?.layoutManager=LinearLayoutManager(context)
        cartItemsList?.adapter=CartAdapter(context!!,message.data)
        progLoading?.visibility=View.GONE

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
  var viewModel:ProductesViewModel?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= ProductRepostary(api!!)
        val factory= ProductViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(ProductesViewModel::class.java)
        viewModel?.Auth=this

//        try {
          viewModel!!.getCart(MainActivity.cacheObj.token)
//        }catch (e:Exception){
//            context?.toast(e.message!!)
//        }

        sendOrder?.setOnClickListener {
            viewModel!!.sendOrder(MainActivity.cacheObj.token)
        }
    }



}
