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
import java.text.SimpleDateFormat
import java.util.*


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

//        2019-09-26T12:56:05.000Z

        var dateLast=data.createdAt !!.substring(0,10)+" "+data.createdAt!!.substring(11,19)
        //val toyBornTime = "2014-06-18 12:56:50"
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss"
        )
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        val oldDate = dateFormat.parse(dateLast)
        println("ddddddddddddddddddddddddddddddddddd")
        println(oldDate)

//        var dt = Date()
//        val c = Calendar.getInstance()
//        c.time = dt
//        c.add(Calendar.DATE, 1)
//        dt = c.time

        val current = Date()
        println(current)

        val diff = current.time - oldDate.time
        val seconds = (diff / 1000 ).toInt()
        val minutes = (seconds / 60) .toInt()
        val hours = (minutes / 60) .toInt()
        val days:Int = (hours / 24) .toInt()
        println("diffrent  "+days)




        if(days!=0){
            holder.view.dayNum.text="قبل $days يوم "
        }else if(hours!=0){
            holder.view.dayNum.text="قبل $hours ساعات  "
        }else{
            holder.view.dayNum.text="قبل $minutes دقائق "
        }
        holder.view.priceOrderCard.text=data.totalPrice .toString()+" IQD"
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

