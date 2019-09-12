package com.mustafayusef.wakely.ui.orders


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.order.Data
import kotlinx.android.synthetic.main.order_card.view.*


class ordersAdapter(
    val context: Context,
    val response: List<Data>
) : RecyclerView.Adapter<ordersAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.order_card ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
       var data=response .get(position)

        holder.view.priceOrderCard.text=data.totalPrice .toString()+" $"
       // holder.view.dayNum.
        holder.view.orderQuantity.text=data.totalQuantities .toString()
//        Glide.with(context).load("http://api.centralmarketiq.com/"+carsP.image+".png").into(holder.view?.numImage)

        holder.view.setOnClickListener {
           var bundel=Bundle()
            bundel.putSerializable("orders",data)
            holder.view.findNavController()?.navigate(R.id.detailsOrder,bundel)
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

