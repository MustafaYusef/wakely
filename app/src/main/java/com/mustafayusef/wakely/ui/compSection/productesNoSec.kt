package com.mustafayusef.wakely.ui.compSection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.cartResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.ordersResponse
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.Products.*
import com.mustafayusef.wakely.ui.compSection.SecRepostary
import com.mustafayusef.wakely.ui.compSection.SectionViewModelFactory
import kotlinx.android.synthetic.main.productes_fragment.*
import kotlinx.android.synthetic.main.progress.*

class productesNoSec : Fragment(), productLesener {
    override fun OnSuccessDisscount(message: productsResponse) {
        productList?.layoutManager=LinearLayoutManager(context!!)
        productList?.adapter= productsAdapter(context!!,message)
        progLoading?.visibility=View.GONE
    }


    override fun OnSuccessSend(message: productsResponse) {

    }

    override fun OnSuccessGetCart(message: Cart) {

    }

    override fun OnSuccessAdd(message: productsResponse) {

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: productsResponse) {
        productList?.layoutManager=LinearLayoutManager(context!!)
        productList?.adapter=productsAdapter(context!!,response)
        progLoading?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
    }

    companion object {
        fun newInstance() = productesNoSec()
    }

    private lateinit var viewModel: ProductesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.productes_fragment, container, false)
    }

    var flage=false
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        flage= arguments?.getBoolean("flage")!!
        var id=arguments!!.getString("secId")
//        viewModel = ViewModelProviders.of(this).get(ProductesViewModel::class.java)

        titSec?.text=arguments!!.getString("name")
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= ProductRepostary(api!!)
        val factory= ProductViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(ProductesViewModel::class.java)
        viewModel?.Auth=this
        if(flage){
            viewModel?.getDisscountProducts(id!!)
        }else{
            viewModel?.getProduct(MainActivity.cacheObj.token,id!!,0,0)
        }

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.productesNoSec) {
               // bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
              //  bottomNav?.visibility = View.VISIBLE
                toolbar?.visibility = View.VISIBLE
            }
        }

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
