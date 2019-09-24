package com.mustafayusef.wakely.ui.Products


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.productsResponse
import kotlinx.android.synthetic.main.product_card.view.*
import kotlinx.android.synthetic.main.section_card.view.*


class productsAdapter(
    val context: Context,
    val response: productsResponse
) : RecyclerView.Adapter<productsAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.product_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.data.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response.data.get(position)
        if(data.description.length>20){
            holder.view.descProd.text=data.description.subSequence(0,20).toString()+".."
        }else{
            holder.view.descProd.text=data.description
        }

        holder.view.prodTitle.text=data.title
        holder.view.priceProd.text= data?.productPrices[0]!!.price.toString()!!
        Glide.with(context).load("http://api.alwakiel.com/storage/images/"+
                data.productPrices[0].image)
            .into(  holder.view.circleImageViewProd)
        holder.view.setOnClickListener {
            var bundel=Bundle()
            bundel.putSerializable("product",data)
            holder.view.findNavController().navigate(R.id.productDetails,bundel)
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

