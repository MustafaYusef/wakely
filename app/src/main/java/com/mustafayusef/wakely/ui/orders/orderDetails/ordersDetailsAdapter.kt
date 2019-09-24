package com.mustafayusef.wakely.ui.orders.orderDetails


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.order.Order
import kotlinx.android.synthetic.main.order_details_card.view.*


class ordersDetailsAdapter(
    val context: Context,
    val response: List<Order>
) : RecyclerView.Adapter<ordersDetailsAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.order_details_card ,parent,false)

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
        holder.view. tileCompO.text=data.companyDetails.title
        holder.view.PriceO.text=data.price  .toString()+" $"
       // holder.view.dayNum.
        holder.view.quantityO.text=data.totalQuantities .toString()

        if(data.status==0){
            holder.view. statusO.setBackgroundResource(R.drawable.status_btn_waiting)
            holder.view. statusO.setTextColor(context.resources .getColor(R.color.yellow))
            holder.view. statusO.text="أنتضار"
        }else if(data.status==2){
            holder.view. statusO.setBackgroundResource(R.drawable.status_btn_refuse)
            holder.view. statusO.setTextColor(context.resources .getColor(R.color.red))
            holder.view. statusO.text="مرفوض"
        }
        holder.view.setOnClickListener {
            var bundel=Bundle()
            bundel.putSerializable("products",data)
            holder.view.findNavController()?.navigate(R.id.orderProduct,bundel)
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

