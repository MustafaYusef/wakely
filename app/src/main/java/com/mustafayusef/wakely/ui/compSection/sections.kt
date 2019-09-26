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
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.data.singal.singelCompany
import com.mustafayusef.wakely.network.myApi
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.progress.*

class sections : Fragment(),SecLesener {
    override fun OnSuccessRate(message: acceptRes) {
        context?.toast(message.message)
        progLoading?.visibility=View.GONE
        viewModel.getCtegore(MainActivity.cacheObj.token,id!!)
    }

    override fun OnSuccessProduct(response: productsResponse) {

    }


    companion object {
        fun newInstance() = sections()
    }

    private lateinit var viewModel: SectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }
    var image:String?=null
    var name:String?=null
    var id:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= SecRepostary(api!!)
        val factory= SectionViewModelFactory(repostary)

       id=arguments!!.getString("CompId")
         image=arguments!!.getString("image")
         name=arguments!!.getString("name")



        viewModel = ViewModelProviders.of(this,factory).get(SectionsViewModel::class.java)
        viewModel?.Auth=this
        viewModel.getCtegore(MainActivity.cacheObj.token,id!!)



        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.sections) {
              //  bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
              //  bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }

      //  viewModel = ViewModelProviders.of(this).get(SectionsViewModel::class.java)

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(
        response: categoreResponse,
        singelRes: singelCompany
    ) {

        if(response.data.count()<=1){
            var bundel=Bundle()
            bundel.putString("secId",response.data[0] ._id)
            bundel.putString("name",response.data[0].name)
        view?.findNavController()?.navigate(R.id.productesNoSec,bundel)
        }else{
            ordersList?.layoutManager=LinearLayoutManager(context!!)

            ordersList?.adapter=MainSecAdapter(context!!,response,image,name,singelRes,viewModel)
        }


        progLoading?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
    }
}
