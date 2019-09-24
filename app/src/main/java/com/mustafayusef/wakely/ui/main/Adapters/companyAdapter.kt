package com.mustafayusef.wakely.ui.main.Adapters


import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.ShopsResponse
import kotlinx.android.synthetic.main.store_comp_card.view.*


class companyAdapter(
    val context: Context,
    val companyResponse: ShopsResponse
) : RecyclerView.Adapter<companyAdapter.CustomViewHolder>(){
//
//  private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.store_comp_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return companyResponse.data.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        val comp=companyResponse.data.get(position)

        if(comp.title.length>14){
            holder.view. storeTitle?.text=comp.title.subSequence(0,13).toString()+".."
        }else{
            holder.view. storeTitle?.text=comp.title
        }
        holder.view.discStore.visibility=View.GONE

        Glide.with(context).load("http://api.alwakiel.com/storage/images/"+comp.image)
            .into(holder.view?.storeImage)
        holder.view.setOnClickListener {
            if(MainActivity.cacheObj.token!=""&&(MainActivity.cacheObj.role==0||MainActivity.cacheObj.role==2)){
                var bundel=Bundle()
                bundel.putString("name",comp.title)
                bundel.putString("CompId",comp._id)
                bundel.putString("image",comp.image)
                holder.view.findNavController()?.navigate(R.id.sections,bundel)

            }else{
                context?.toast("غير مسموح")
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

