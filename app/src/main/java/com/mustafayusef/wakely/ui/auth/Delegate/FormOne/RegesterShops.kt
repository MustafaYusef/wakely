package com.mustafayusef.wakely.ui.auth.Delegate.FormOne

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.AddUserRes
import com.mustafayusef.wakely.data.provRes
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.Delegate.DelegateLesener
import com.mustafayusef.wakely.ui.auth.Delegate.RegShopsViewModelFactory
import com.mustafayusef.wakely.ui.auth.Delegate.RegesterShopsViewModel
import kotlinx.android.synthetic.main.regester_shops_fragment.*

class RegesterShops : Fragment(),DelegateLesener {
    override fun OnSuccessProv(response: provRes) {
            context?.toast(response.toString())
    }

    override fun OnSuccessCity(response: provRes) {

    }

    override fun OnStart() {
        context?.toast("Start")
    }

    override fun OnSuccess(response: AddUserRes) {
        context?.toast(response.message.toString())
        println(response)
        var bundel=Bundle()
        bundel.putString("UserId",response.data.id)
        view?.findNavController()?.navigate(R.id.regesterShopsTow,bundel)
    }

    override fun onFailer(message: String) {
        context?.toast(message)
    }

    override fun onFailerNet(message: String) {
        context?.toast("مشكلة في الأتصال")

    }

    companion object {
        fun newInstance() = RegesterShops()
    }

    private lateinit var viewModel: RegesterShopsViewModel
   var name=""
    var phone=""
    var pass=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.regester_shops_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.regesterShops) {
                bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= AuthRepostary(api!!)
        val factory= RegShopsViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(RegesterShopsViewModel::class.java)

        viewModel?.Auth=this
        loginBtnReges?.setOnClickListener {
            if(nameReg?.text.toString().trim()!=""&&phoneReg?.text.toString().trim()!=""
                &&passReg?.text.toString().trim()!=""){
                 name=nameReg?.text.toString()
                 phone=phoneReg?.text.toString()
                 pass=passReg?.text.toString()
//                viewModel.AddUser( name ,phone ,pass,1)
                view?.findNavController()?.navigate(R.id.regesterShopsTow)

            }else{

            }

        }
    }

}
