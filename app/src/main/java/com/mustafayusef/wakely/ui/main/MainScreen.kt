package com.mustafayusef.wakely.ui.main

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
import com.mustafayusef.wakely.data.BannersResponse
import com.mustafayusef.wakely.data.ShopsResponse
import com.mustafayusef.wakely.data.disscountCompany
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.main.Adapters.MainScreenAdapter
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.android.synthetic.main.progress.*
import android.widget.Toast




class MainScreen : Fragment(),MainScreenAdapter.OnNoteLisener,MainLesener {
    override fun onSuccessAllComp(message: ShopsResponse) {

    }

    override fun onSuccessAll(message: ShopsResponse) {

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(
        response: BannersResponse,
        shopsResponse: ShopsResponse,
        companyResponse: ShopsResponse,
        disscountResponse: disscountCompany
    ) {

        ListMain?.adapter = MainScreenAdapter(context!!,response,shopsResponse,companyResponse
            ,disscountResponse, this)
        progLoading?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
        context?.toast(message)
    }


    companion object {
        fun newInstance() = MainScreen()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }
var flage=false
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getBoolean("flage",false)?.let {
            flage=it
        }

        if(MainActivity.cacheObj.role==1&&MainActivity.cacheObj.token!=""){
            view?.findNavController()?.navigate(R.id.regesterShops)
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

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= MainRepostary(api!!)
        val factory= MainViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(MainScreenViewModel::class.java)
        viewModel?.Auth=this

        ListMain?.layoutManager = LinearLayoutManager(context)
        viewModel?.GetMain()

//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.login) {
//                view?.findNavController()?.navigate(R.id.outFrag)
//            }
//
//        }


    }

    override fun onNoteClick(position: Int) {

    }

}
