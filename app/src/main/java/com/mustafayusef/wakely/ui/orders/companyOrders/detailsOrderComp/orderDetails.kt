package com.mustafayusef.wakely.ui.orders.companyOrders.detailsOrderComp

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.companyOrder.Data
import com.mustafayusef.wakely.data.companyOrder.companyOrderResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.orders.companyOrders.OrdersCompanyLesener
import com.mustafayusef.wakely.ui.orders.companyOrders.OrdersCompanyRepostary
import com.mustafayusef.wakely.ui.orders.companyOrders.OrdersCompanyViewModel
import com.mustafayusef.wakely.ui.orders.companyOrders.OrdersCompanyViewModelFactory
import kotlinx.android.synthetic.main.fragment_order_details.*
import kotlinx.android.synthetic.main.pop_up_accept.view.*
import kotlinx.android.synthetic.main.progress.*


class orderDetails : Fragment(),OrdersCompanyLesener {
    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }

    override fun OnSuccess(message: companyOrderResponse) {

    }

    override fun OnSuccessAccept(message: acceptRes) {
        progLoading?.visibility=View.GONE
        view?.findNavController()?.navigate(R.id.ordersCompany)
        //context?.toast("تمت الموافقة على الطلب")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false)
    }
    var order:Data?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.orderDetails) {
                bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE
                toolbar?.visibility = View.VISIBLE
            }

        }


        order= arguments?.getSerializable("orderCompany") as Data






        productOrderList?.layoutManager=LinearLayoutManager(context)

        productOrderList?.adapter=productsComOrderAdapter(context!!,order!!.productDetails,
            order!!.products, order!!
        )
    }
    private lateinit var viewModel: OrdersCompanyViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(order?.status==1){
            acceptOrder?.visibility=View.GONE
        }else if(order?.status==2){
            refuseOrder?.visibility=View.GONE
        }
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= OrdersCompanyRepostary(api!!)
        val factory= OrdersCompanyViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(OrdersCompanyViewModel::class.java)
        viewModel?.Auth=this

        acceptOrder?.setOnClickListener {
            showDilog(1)

        }
        refuseOrder?.setOnClickListener {
            showDilog(2)
            //viewModel?.Accept(MainActivity.cacheObj.token,order!!._id,2,"accepted")
        }
    }
    fun showDilog(flage:Int) {
        val dview: View = layoutInflater.inflate(R.layout.pop_up_accept, null)
        val builder = AlertDialog.Builder(context!!).setView(dview)
        val malert= builder.show()
        malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dview.confirm?.setOnClickListener {
            if(!dview.nots.text.toString().isNullOrEmpty()){
                viewModel?.Accept(MainActivity.cacheObj.token,order!!._id,flage,
                    dview.nots.text.toString())
                malert.dismiss()
            }else{
                viewModel?.Accept(MainActivity.cacheObj.token,order!!._id,flage,
                    " ")
                malert.dismiss()
            }

        }
    }
}
