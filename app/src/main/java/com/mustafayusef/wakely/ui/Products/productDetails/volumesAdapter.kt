package com.mustafayusef.wakely.ui.Products.productDetails

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.ProductPrice
import kotlinx.android.synthetic.main.vol_layout.view.*


class volumesAdapter(val context:Context, var onNoteLisener: OnNoteLisener, var data: List<ProductPrice>) : RecyclerView.Adapter<volumesAdapter.CustomViewHolder>(){
    //
    private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.vol_layout ,parent,false)

        return CustomViewHolder(
            cardItem,
            mOnNotlesener
        )
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return data.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))

       var data=data.get(position)
       holder.view. btn_volum.text=data.shortDescription
//        Glide.with(context).load(holidays?.logoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.LogoAir)

//        holder.view. btn_volum.setOnClickListener {
//            holder.view. btn_volum.setBackgroundResource(R.drawable.round_btn)
//        }
       // Glide.with(context).load(bitmapDrawable).into(holder.view?.carImage1)

    }



    class CustomViewHolder(val view : View, var onNoteLisener: OnNoteLisener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        var OnNotlesener: OnNoteLisener
        override fun onClick(view: View?) {
            onNoteLisener.onNoteClick(layoutPosition )
        }

        init {
            this.OnNotlesener=onNoteLisener
            view.btn_volum.setOnClickListener(this)

        }


    }


    interface OnNoteLisener {
        fun onNoteClick( position: Int)
    }



}

