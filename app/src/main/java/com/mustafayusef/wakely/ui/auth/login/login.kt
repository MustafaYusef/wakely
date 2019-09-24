package com.mustafayusef.wakely.ui.auth.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.profile.profile
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.AuthViewModel
import com.mustafayusef.wakely.ui.auth.AuthViewModelFactory
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.progress.*

class login : Fragment(),AuthLesener {
    override fun OnSuccessUpdate(response: loginResponse) {

    }

    override fun OnSuccessProfile(message: profile) {

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: loginResponse) {
        progLoading?.visibility=View.GONE
    //    context?.toast(response.data.token)
        MainActivity.cacheObj.token=response.data.token
        MainActivity.cacheObj.role=response.data.role
        println("tooooooooooooooooooooooooken    "+response.data.token)
          if(MainActivity.cacheObj.role==1){
              view?.findNavController()?.navigate(R.id.regesterShops)
          }else if(MainActivity.cacheObj.role==0||MainActivity.cacheObj.role==2) {
              view?.findNavController()?.navigate(R.id.main_fragment)
          }

    }

    override fun onFailer(message: String) {
        context?.toast("هناك مشكلة ما")
        progLoading?.visibility=View.GONE

    }

    override fun onFailerNet(message: String) {
        context?.toast("مشكلة في الأتصال")
        progLoading?.visibility=View.GONE
    }

    companion object {
        fun newInstance() = login()
    }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.login_fragment, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)



        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= AuthRepostary(api!!)
        val factory= AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        viewModel?.Auth=this

        loginBtn?.setOnClickListener {
            if(phoneLogin.text.toString().isNullOrEmpty()){
                context?.toast("يرجى أدخال رقم الهاتف")

            }else if(passLogin.text.toString().isNullOrEmpty()){
                context?.toast("يرجى أدخال كلمة المرور")
            }else if(phoneLogin.text.toString().length<10){
                context?.toast("الرقم غير صحيح")
            }else{
                val playerId:String= OneSignal.getPermissionSubscriptionState().subscriptionStatus.userId
                viewModel.Login(phoneLogin.text.toString(),passLogin.text.toString(),playerId)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)


        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.login) {
                bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }
        goToMain.setOnClickListener {
            var bundel=Bundle()
            bundel.putBoolean("flage",true)
            view?.findNavController().navigate(R.id.main_fragment,bundel)
        }
        toReg.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_login_to_reqester)
        }

    }

}
