package com.mustafayusef.wakely.ui.main.showAllCompany

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.BannersResponse
import com.mustafayusef.wakely.data.ShopsResponse
import com.mustafayusef.wakely.data.disscountCompany
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.main.Adapters.companyAdapter
import com.mustafayusef.wakely.ui.main.Adapters.shopsAdapter
import com.mustafayusef.wakely.ui.main.MainLesener
import com.mustafayusef.wakely.ui.main.MainRepostary
import com.mustafayusef.wakely.ui.main.MainScreenViewModel
import com.mustafayusef.wakely.ui.main.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_show_all.*
import kotlinx.android.synthetic.main.progress.*


class showAllComp : Fragment(),MainLesener {
    override fun onSuccessAllComp(message: ShopsResponse) {
        calculateNoOfColumns(context!!,170f)
        showAllList?.layoutManager= GridLayoutManager(context!!,calculateNoOfColumns(context!!,170f))
        showAllList?.adapter=companyAdapter(context!!,message)
        progLoading?.visibility=View.GONE
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

    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE
    }

    override fun onSuccessAll(message: ShopsResponse) {

    }

    private lateinit var viewModel: MainScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all, container, false)
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

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= MainRepostary(api!!)
        val factory= MainViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(MainScreenViewModel::class.java)
        viewModel?.Auth=this

        viewModel?.GetAllCompany()

    }
    fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int { // For example columnWidthdp=180
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }
}
