package com.mustafayusef.wakely.ui.orders

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
import com.mustafayusef.wakely.data.order.ordersRes
import com.mustafayusef.wakely.network.myApi
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.progress.*

class Orders : Fragment(),OrdersLesener {
    override fun OnStart() {
      // context?.toast("start")
        progLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }



    override fun OnSuccessGetOrders(message: ordersRes) {
        ordersList?.layoutManager=LinearLayoutManager(context)
        ordersList?.adapter=ordersAdapter(context!!,message.data)
        progLoading?.visibility=View.GONE
    }
    companion object {
        fun newInstance() = Orders()
    }

    private lateinit var viewModel: OrdersViewModel

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
        val repostary= OrdersRepostary(api!!)
        val factory= OrdersViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(OrdersViewModel::class.java)
        viewModel?.Auth=this
        viewModel.getOrdersHistory(MainActivity.cacheObj.token)
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
