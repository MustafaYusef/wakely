package com.mustafayusef.wakely.ui.compSection


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.holidaymaster.utils.calculateNoOfColumns
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.categoreResponse
import kotlinx.android.synthetic.main.first_section.view.*
import kotlinx.android.synthetic.main.section_card.view.*
import kotlinx.android.synthetic.main.sections_fragment.*


class SectionsAdapter(
    val context: Context,
    val data: categoreResponse
) : RecyclerView.Adapter<SectionsAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)


        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.section_card ,parent,false)
        return  CustomViewHolder(cardItem)


    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return data.data.size

    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))


           var data= data.data.get(position)
           holder.view.secTitle.text=data.name
           Glide.with(context).load("http://api.alwakiel.com/storage/images/"+data.image)
               .into(  holder.view.circleImageViewSec)
           holder.view.setOnClickListener {
               var bundel=Bundle()
               bundel.putString("secId",data._id)
               bundel.putString("name",data.name)
               holder.view?.findNavController().navigate(R.id.productes,bundel)

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

