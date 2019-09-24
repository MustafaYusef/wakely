package com.mustafayusef.wakely.ui.orders.orderDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.wakely.MainActivity
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.order.Order
import kotlinx.android.synthetic.main.orders_fragment.*

class orderProduct : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val itemComp= activity?.findViewById<View>(R.id.ordersCompany)
        val item= activity?.findViewById<View>(R.id.orders_fragment)
        if(MainActivity.cacheObj.role!=2){
            item?.visibility=View.VISIBLE
            itemComp?.visibility=View.GONE
        }else{
            item?.visibility=View.GONE
            itemComp?.visibility=View.VISIBLE
        }


        var products: Order =arguments!!.getSerializable("products") as Order
        ordersList?.layoutManager=LinearLayoutManager(context)
        ordersList?.adapter=productsOrderAdapter(context!!,products.productDetails,products.products)
    }
    }

