package com.mustafayusef.wakely.ui.orders.companyOrders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.companyOrder.companyOrderResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.network.myApi
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.progress.*

class ordersCompany : Fragment(),OrdersCompanyLesener {
    override fun OnSuccessAccept(message: acceptRes) {

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
      context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }

    override fun OnSuccess(message: companyOrderResponse) {
        progLoading?.visibility=View.GONE
      ordersList?.layoutManager=LinearLayoutManager(context)
       ordersList?.adapter= ordersCompanyAdapter(context!!,message.data)
    }

    companion object {
        fun newInstance() = ordersCompany()
    }

    private lateinit var viewModel: OrdersCompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= OrdersCompanyRepostary(api!!)
        val factory= OrdersCompanyViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(OrdersCompanyViewModel::class.java)
        viewModel?.Auth=this
        viewModel?.getOrdersCompany(MainActivity.cacheObj.token)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemComp= activity?.findViewById<View>(R.id.ordersCompany)
        val item= activity?.findViewById<View>(R.id.orders_fragment)
        if(MainActivity.cacheObj.role!=2){
            item?.visibility=View.VISIBLE
            itemComp?.visibility=View.GONE
        }else{
            item?.visibility=View.GONE
            itemComp?.visibility=View.VISIBLE
        }
    }
}
