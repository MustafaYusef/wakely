package com.mustafayusef.wakely.ui.compSection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.sections_fragment.*

class sections : Fragment(),SecLesener {


    companion object {
        fun newInstance() = sections()
    }

    private lateinit var viewModel: SectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sections_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= SecRepostary(api!!)
        val factory= SectionViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(SectionsViewModel::class.java)
        viewModel?.Auth=this
        viewModel.getCtegore(MainActivity.cacheObj.token,"5d5b29778e1c8716985c1830")



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

    }

    override fun OnSuccess(response: categoreResponse) {

        listSections?.layoutManager=GridLayoutManager(context!!,2)
        listSections?.adapter=SectionsAdapter(context!!,response.data)
    }

    override fun onFailer(message: String) {
    }

    override fun onFailerNet(message: String) {
    }
}
