package com.mustafayusef.wakely.ui.main.Adapters


import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.disscountCompany
import kotlinx.android.synthetic.main.store_comp_card.view.*


class spicialAdapter(
    val context: Context,
    val disscountResponse: disscountCompany
) : RecyclerView.Adapter<spicialAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.store_comp_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return disscountResponse.data.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))

        val comp=disscountResponse.data.get(position)

        if(comp.company.title.length>14){
            holder.view. storeTitle?.text=comp.company.title .subSequence(0,13).toString()+".."
        }else{
            holder.view. storeTitle?.text=comp.company.title
        }
        holder.view.discStore.text=comp.discountPercentage.toString()+" %"
        Glide.with(context).load("http://api.alwakiel.com/storage/images/"+comp.image)
            .into(holder.view?.storeImage)

//        Glide.with(context).load("http://api.centralmarketiq.com/"+carsP.image+".png").into(holder.view?.numImage)

        holder.view.setOnClickListener {
            if(MainActivity.cacheObj.token!=""){
                var bundel= Bundle()
                bundel.putString("secId",comp._id)
                bundel.putBoolean("flage",true)
                bundel.putString("name",comp.company.title)
                holder.view.findNavController()?.navigate(R.id.productes,bundel)

            }
        }
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

