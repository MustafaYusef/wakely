package com.mustafayusef.wakely.ui.orders.orderDetails


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.order.Product
import com.mustafayusef.wakely.data.order.ProductDetail
import kotlinx.android.synthetic.main.product_card.view.*
import kotlinx.android.synthetic.main.product_card.view.circleImageViewProd
import kotlinx.android.synthetic.main.product_card.view.priceProd
import kotlinx.android.synthetic.main.product_card.view.prodTitle
import kotlinx.android.synthetic.main.product_card_order.view.*


class productsOrderAdapter(
    val context: Context,
    val response: List<ProductDetail>,val response1: List<Product>
) : RecyclerView.Adapter<productsOrderAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.product_card_order ,parent,false)

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
       var data=response.get(position)
        var data1=response1.get(position)
//        if(data.length>20){
//            holder.view.descProd.text=data.subSequence(0,20).toString()+".."
//        }else{
//            holder.view.descProd.text=data.description
//        }

//                holder.view.priceProd.text= data?.price.toString()!!

        holder.view. priceProd.text=data1.price.toString()+" IQD"
        holder.view. quantityOr.text=data1.quantity.toString()
        holder.view. prodTitle.text=data.title
        holder.view.volum.text=data.productPrices[0].shortDescription
        for(i in 0 until data.productPrices.count()){
           if(data.productPrices[i]._id==data1.priceId) {
               holder.view.volum.text=data.productPrices[0].shortDescription
               Glide.with(context).load("http://api.alwakiel.com/storage/images/"+
                       data.productPrices[0].image)
                   .into(  holder.view.circleImageViewProd)
           }
        }





//        holder.view.prodTitle.text=data.
//        holder.view.priceProd.text= data?.productPrices[0]!!.price.toString()!!
//        Glide.with(context).load("https://alwakel.herokuapp.com/storage/images/"+
//                data.productPrices[0].image)
//            .into(  holder.view.circleImageViewProd)


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

