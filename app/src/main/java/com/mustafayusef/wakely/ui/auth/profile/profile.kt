package com.mustafayusef.wakely.ui.auth.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.AuthViewModel
import com.mustafayusef.wakely.ui.auth.AuthViewModelFactory
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.slider_layout.view.*

class profile : Fragment() ,AuthLesener{
    override fun OnSuccessUpdate(response: loginResponse) {

    }


    companion object {
        fun newInstance() = profile()
    }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
           if(MainActivity.cacheObj.token==""){
               view?.findNavController()?.navigate(R.id.login)
               context?.toast("يجب ان تمتلك حساب")
           }

        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.profile_fragment) {
               // bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
              //  bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }

        if(MainActivity.cacheObj.role==2){
            titProfile?.text="معلومات الشركة"
            infop?.text="معلومات مدير المبيعات"
            Email?.visibility=View.VISIBLE

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
        val repostary= AuthRepostary(api!!)
        val factory= AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        viewModel?.Auth=this

        viewModel?.Profile(MainActivity.cacheObj.token)

        EditeBtn?.setOnClickListener {
            var bundel=Bundle()
            bundel.putString("name",pro?.data?.user?.name)
            bundel.putString("title",pro?.data?.company?.title)
            bundel.putString("phone",pro?.data?.company?.phone)
            bundel.putString("image",pro?.data?.company?.image)
            view?.findNavController()?.navigate(R.id.updateProfile,bundel)
        }
        logOut?.setOnClickListener {
            MainActivity.cacheObj.token=""
            MainActivity.cacheObj.role=-1
            view?.findNavController()?.navigate(R.id.login)
        }
    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: loginResponse) {



    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast("مشكلة في الأتصال")
        progLoading?.visibility=View.GONE
    }
    var pro:com.mustafayusef.wakely.data.profile.profile?=null
    override fun OnSuccessProfile(message: com.mustafayusef.wakely.data.profile.profile) {
        profCon?.visibility=View.VISIBLE
        pro=message
        profileImage?.let {it1->
            context?.let {
                Glide.with(it).load("http://api.alwakiel.com/storage/images/"+message.data.company.image)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into( it1)
            }
        }

        Email?.text=message.data.user.email

        titleShopsP?.text=message.data.company.title
        phoneShop?.text=message.data.company.phone
        locationRegP?.text=message.data.company.fullLocation.province+" / "+message.data.company.fullLocation.city

        nameUser?.text=message.data.user.name
        PhoneUser?.text=message.data.user.phone
        progLoading?.visibility=View.GONE
    }
}
