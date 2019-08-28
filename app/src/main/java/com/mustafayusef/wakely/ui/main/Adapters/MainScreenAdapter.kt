package com.mustafayusef.wakely.ui.main.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.wakely.R
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.first_banners.view.*
import kotlinx.android.synthetic.main.shops_list.view.*

class MainScreenAdapter(
    val context: Context, var onNoteLisener: OnNoteLisener
) : RecyclerView.Adapter<MainScreenAdapter.CustomViewHolder>(){
//
  private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)
         if(viewType==0){
             val layoutInflater =LayoutInflater.from(parent.context)
             val cardItem=layoutInflater.inflate(R.layout.first_banners ,parent,false)

             return  CustomViewHolder(cardItem,mOnNotlesener)
         }
        else{
            val layoutInflater =LayoutInflater.from(parent.context)
             val cardItem=layoutInflater.inflate(R.layout.shops_list,parent,false)

             return  CustomViewHolder(cardItem,mOnNotlesener)
         }


    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return 4

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        } else {
            return 1
        }
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
      if(position==0){
          //animation_loadingMain?.visibility=View.GONE
          val adapter = bannersAdapter(context!!)

          holder.view.storesSlider?.sliderAdapter = adapter

          //  context?.let { Glide.with(it).load(com.mustafayusef.sharay.R.drawable.car).into(carImageD) }
//          holder.view.storeSlider?.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//          holder.view.storeSlider?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
         holder.view. storesSlider?.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
          holder.view.storesSlider?.indicatorSelectedColor = Color.WHITE
          holder.view. storesSlider?.indicatorUnselectedColor = Color.GRAY
          holder.view. storesSlider?.scrollTimeInSec = 3 //set scroll delay in seconds :
          holder.view. storesSlider?.startAutoCycle()
      }
        else if(position==1){
          holder.view.shopsList?.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
          holder.view.shopsList?.adapter=companyAdapter(context)
          holder.view.titleCom?.text="الشركات"
      }
      else if(position==2){
          holder.view.shopsList?.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
          holder.view.shopsList?.adapter=shopsAdapter(context)
          holder.view.titleCom?.text="المحلات"
      }
      else if(position==3){
          holder.view.shopsList?.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
          holder.view.shopsList?.adapter=spicialAdapter(context)
          holder.view.titleCom?.text="العروض"
      }

    }



    class CustomViewHolder(val view : View, var onNoteLisener: OnNoteLisener ) : RecyclerView.ViewHolder(view), View.OnClickListener{
           var OnNotlesener:OnNoteLisener
        override fun onClick(view: View?) {
            if (layoutPosition!=0)
           onNoteLisener.onNoteClick(layoutPosition)
        }

        init {
            this.OnNotlesener=onNoteLisener
         view.setOnClickListener(this)

        }


    }


    interface OnNoteLisener {
        fun onNoteClick( position: Int)
    }



}

