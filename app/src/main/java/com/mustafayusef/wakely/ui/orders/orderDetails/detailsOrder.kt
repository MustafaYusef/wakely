package com.mustafayusef.wakely.ui.orders.orderDetails

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
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.order.Data
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.orders.OrdersRepostary
import com.mustafayusef.wakely.ui.orders.OrdersViewModel
import com.mustafayusef.wakely.ui.orders.OrdersViewModelFactory
import kotlinx.android.synthetic.main.orders_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class detailsOrder : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var orders=arguments!!.getSerializable("orders") as Data

        ordersList?.layoutManager=LinearLayoutManager(context)
        ordersList?.adapter=ordersDetailsAdapter(context!!,orders.order)
    }
}
