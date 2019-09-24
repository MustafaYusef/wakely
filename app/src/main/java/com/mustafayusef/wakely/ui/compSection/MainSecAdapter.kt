package com.mustafayusef.wakely.ui.compSection


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.holidaymaster.utils.calculateNoOfColumns
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.singal.singelCompany
import kotlinx.android.synthetic.main.first_section.view.*
import kotlinx.android.synthetic.main.sections_fragment.view.*
import android.widget.Toast
import com.mustafayusef.wakely.MainActivity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.network.myApi
import kotlinx.android.synthetic.main.pop_up_accept.view.*
import kotlinx.android.synthetic.main.rate_pop.view.*


class MainSecAdapter(
    val context: Context,
    val data: categoreResponse,
    val image: String?,
    val name: String?,
    val singelRes: singelCompany,var viewModel: SectionsViewModel
) : RecyclerView.Adapter<MainSecAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener









    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

    if(viewType==0){
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.first_section ,parent,false)
        return  CustomViewHolder(cardItem)
    }else{
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.sections_fragment ,parent,false)
        return  CustomViewHolder(cardItem)
    }

    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return 2

    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return 0
        }else{
            return 1
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       if(position==0){
           Glide.with(context!!).load("http://api.alwakiel.com/storage/images/"+image)
               .into(holder.view.imgSec)
           holder.view.compTitle.text=name
           holder.view.ratAv.text="التقييم: "+singelRes.data.rate.toString()
           holder.view.whatsApp?.setOnClickListener {
               val contact =singelRes.data.phone  // use country code with your phone number
               val url = "https://api.whatsapp.com/send?phone=$contact"
               try {
                   val pm = context.packageManager
                   pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                   val i = Intent(Intent.ACTION_VIEW)
                   i.data = Uri.parse(url)
                  context.startActivity(i)
               } catch (e: PackageManager.NameNotFoundException) {
                   context.toast("لا يوجد whatsapp في جهازك")
                   e.printStackTrace()
               }
           }
           holder.view.rate?.setOnClickListener {
               val dview: View = View.inflate(context, R.layout.rate_pop, null)
               val builder = AlertDialog.Builder(context!!).setView(dview)
               val malert= builder.show()
               malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

               dview.confirmRate?.setOnClickListener {

                   viewModel!!.addRate(MainActivity.cacheObj.token,dview.Rate.rating.toDouble(),singelRes.data._id)

                   malert?.dismiss()
               }
           }

       }else{

           holder.view. listSections?.layoutManager= GridLayoutManager(context!!,
               calculateNoOfColumns(context!!,180f)
           )
             holder.view.listSections?.adapter=SectionsAdapter(context!!,data)


//           holder.view.secTitle.text=data.name
//           Glide.with(context).load("https://alwakel.herokuapp.com/storage/images/"+data.image)
//               .into(  holder.view.circleImageViewSec)
//           holder.view.setOnClickListener {
//               var bundel=Bundle()
//               bundel.putString("secId",data._id)
//               bundel.putString("name",data.name)
//               holder.view?.findNavController().navigate(R.id.productes,bundel)
//           }
       }


//        Glide.with(context).load("http://api.centralmarketiq.com/"+carsP.image+".png").into(holder.view?.numImage)

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
//           var OnNotlesener:OnNoteLisener
//        override fun onClick(view: View?) {
//           onNoteLisener.onNoteClick(layoutPosition)
//        }
//
//        init {
//            this.OnNotlesener=onNoteLisener
//         view.setOnClickListener(this)
//
//        }


    }






}

