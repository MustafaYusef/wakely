package com.mustafayusef.wakely.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.BannersResponse
import com.mustafayusef.wakely.data.ShopsResponse
import com.mustafayusef.wakely.data.disscountCompany
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.main.Adapters.MainScreenAdapter
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.android.synthetic.main.progress.*

class MainScreen : Fragment(),MainScreenAdapter.OnNoteLisener,MainLesener {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progLoading?.progress=2
//        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= MainRepostary(api!!)
        val factory= MainViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(MainScreenViewModel::class.java)
        viewModel?.Auth=this

        ListMain?.layoutManager = LinearLayoutManager(context)
        viewModel?.GetMain()

    }
    override fun onNoteClick(position: Int) {

    }
}
