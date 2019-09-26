package com.mustafayusef.wakely.ui.orders.companyOrders.detailsOrderComp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.companyOrder.Data
import com.mustafayusef.wakely.data.order.Product
import com.mustafayusef.wakely.data.order.ProductDetail
import kotlinx.android.synthetic.main.first_order_details.view.*
import kotlinx.android.synthetic.main.product_card.view.*
import kotlinx.android.synthetic.main.product_card.view.circleImageViewProd
import kotlinx.android.synthetic.main.product_card.view.priceProd
import kotlinx.android.synthetic.main.product_card.view.prodTitle
import kotlinx.android.synthetic.main.product_card_order.view.*


class productsComOrderAdapter(
    val context: Context,
    val response: List<com.mustafayusef.wakely.data.companyOrder.ProductDetail>,
    val response1: List<com.mustafayusef.wakely.data.companyOrder.Product>
       ,val order: Data
) : RecyclerView.Adapter<productsComOrderAdapter.CustomViewHolder>(){
//
 // private  var mOnNotlesener=onNoteLisener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

    if(viewType==0){
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.first_order_details ,parent,false)
        return  CustomViewHolder(cardItem)
    }else{
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.product_card_order ,parent,false)

        return  CustomViewHolder(cardItem)
    }

    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
    return response.size+1

    }

    override fun getItemViewType(position: Int): Int {

        if(position==0){
            return 0
        }else{
           return 1
        }
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //holder.view.containerCar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))


        if(position==0){
            holder.view. ShopImageO?.let {it1->
                context?.let {
                    Glide.with(it).load("http://api.alwakiel.com/storage/images/"+
                            order!!.shopDetails.image)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(25)))
                        .into( it1)
                }
            }
            holder.view.titleShopsO?.text=order!!.shopDetails.title
            holder.view.phoneShopO?.text=order!!.shopDetails.phone
            holder.view.locationRegO?.text=order!!.provinceDetails.name+" - "+order!!.cityDetails.name+
                    " - "+order!!.location.nearLocation

            holder.view.TotalPriceO?.text=order!!.price.toString()+" IQD"
        }else{
            var data=response.get(position-1)
            var data1=response1 .get(position-1)
            holder.view. priceProd.text=data1.price.toString()+" IQD"
            holder.view. quantityOr.text=data1.quantity.toString()
            holder.view. prodTitle.text=data.title

            for(i in 0 until data.productPrices.count() ){
                if(data.productPrices[i]._id==data1.priceId) {
                    holder.view.volum.text=data.productPrices[i].shortDescription
                    Glide.with(context).load("http://api.alwakiel.com/storage/images/"+
                            data.productPrices[i].image)
                        .into(  holder.view.circleImageViewProd)
                }
            }
        }
//        if(data.length>20){
//            holder.view.descProd.text=data.subSequence(0,20).toString()+".."
//        }else{
//            holder.view.descProd.text=data.description
//        }

//                holder.view.priceProd.text= data?.price.toString()!!







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

