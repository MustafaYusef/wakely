package com.mustafayusef.wakely.ui.Products.productDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.cart.Product
import com.mustafayusef.wakely.data.cart.ProductPrice
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.Products.ProductRepostary
import com.mustafayusef.wakely.ui.Products.ProductViewModelFactory
import com.mustafayusef.wakely.ui.Products.ProductesViewModel
import com.mustafayusef.wakely.ui.Products.productLesener
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.progress.*


class productDetails2 : Fragment(),
    volumesAdapter.OnNoteLisener,productLesener,View.OnClickListener {

var base:String="http://api.alwakiel.com/storage/images/"
    override fun OnSuccessDisscount(message: productsResponse) {
    }

    override fun OnSuccessSend(message: productsResponse) {

    }

    override fun OnSuccessGetCart(message: Cart) {

    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: productsResponse) {
        progLoading?.visibility=View.GONE
    }

    override fun onFailer(message: String) {
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        progLoading?.visibility=View.GONE

    }

    override fun OnSuccessAdd(message: productsResponse) {
        progLoading?.visibility=View.GONE
        context?.toast(message.message)

    }

    override fun onNoteClick(position: Int) {
        context?.toast(position.toString())
    }
       var num: Int=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    private lateinit var viewModel: ProductesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.productDetails) {
                bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE
                toolbar?.visibility = View.VISIBLE
            }

        }


    }

    var data: Product?=null
    var selectedPrice:Int?=null
    var TotalPrice:Int?=null
    var selectedId:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= ProductRepostary(api!!)
        val factory= ProductViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(ProductesViewModel::class.java)
        viewModel?.Auth=this

         data =arguments!!.getSerializable("product") as Product

        selectedPrice= data!! .productPrices[0].price
        findVolume(data!! .productPrices)

        prodTitleD?.text=data!! .title
        priceDetails?.text=selectedPrice.toString()+"IQD"
        numItem?.text=num.toString()
        totPrice?.text= data!!.productPrices.get(0).price.toString()+"IQD"
        DetailsD?.text=data!!.description

        plusBtn?.setOnClickListener {
            num++
            numItem?.text=num.toString()
            totPrice?.text=(selectedPrice!! *num).toString()
        }
        MinusBtn?.setOnClickListener {
            if(num>1){
                num--
                numItem?.text=num.toString()
                totPrice?.text=(selectedPrice!!*num).toString()
            }

        }
        goToCart?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.cart)
        }
        if(MainActivity.cacheObj.role!=0){
            shopOnly.visibility=View.GONE
        }

        selectedId=data!!.productPrices[0]._id
        vol1.setBackgroundResource(R.drawable.round_btn_pls)

        vol1.setOnClickListener(this)
        vol2.setOnClickListener(this)
        vol3.setOnClickListener(this)
        vol4.setOnClickListener(this)
        vol5.setOnClickListener(this)

//        val adapter = bannersAdapterDetails(context!!,data.productPrices)
//
//        storesSliderD?.sliderAdapter = adapter
//
//        //  context?.let { Glide.with(it).load(com.mustafayusef.sharay.R.drawable.car).into(carImageD) }
////          holder.view.storeSlider?.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
////          holder.view.storeSlider?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
//        storesSliderD?.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
//        storesSliderD?.indicatorSelectedColor = Color.WHITE
//        storesSliderD?.indicatorUnselectedColor = Color.GRAY
//        storesSliderD?.scrollTimeInSec = 3 //set scroll delay in seconds :
//        storesSliderD?.startAutoCycle()

        AddBtnCart?.setOnClickListener {

            viewModel?.AddToCart(MainActivity.cacheObj.token,data!!._id,num,data!!.companyId,selectedId!!)
        }
        Glide.with(context!!).load(base+data!!.productPrices [0].image).
            into(storesSliderD)
    }
    fun setBack(){
        vol1.setBackgroundResource(R.drawable.round_btn_black)
        vol2.setBackgroundResource(R.drawable.round_btn_black)
        vol3.setBackgroundResource(R.drawable.round_btn_black)
        vol4.setBackgroundResource(R.drawable.round_btn_black)
        vol5.setBackgroundResource(R.drawable.round_btn_black)
    }
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.vol1 ->{
                setBack()

                selectedId=data!!.productPrices [0]._id
                vol1.setBackgroundResource(R.drawable.round_btn_pls)
                vol1.text=data!!.productPrices [0].shortDescription
                Glide.with(context!!).load(base+data!!.productPrices [0].image).
                    into(storesSliderD)
                totPrice?.text= ((data!!.productPrices.get(0).price)*num).toString()
                selectedPrice=data!!.productPrices [0].price
                priceDetails?.text=selectedPrice.toString()
            }
            R.id.vol2 ->{
                setBack()
                selectedId=data!!.productPrices [1]._id

                vol2.setBackgroundResource(R.drawable.round_btn_pls)
                vol2.text=data!!.productPrices [1].shortDescription
                Glide.with(context!!).load(base+data!!.productPrices [1].image).
                    into(storesSliderD)
                totPrice?.text= ((data!!.productPrices.get(1).price)*num).toString()
                selectedPrice=data!!.productPrices [1].price
                priceDetails?.text=selectedPrice.toString()
            }
            R.id.vol3 ->{
                setBack()
                selectedId=data!!.productPrices [2]._id
                vol3.setBackgroundResource(R.drawable.round_btn_pls)
                vol3.text=data!!.productPrices [2].shortDescription
                Glide.with(context!!).load(base+data!!.productPrices [2].image).
                    into(storesSliderD)
                totPrice?.text= ((data!!.productPrices.get(2).price)*num).toString()
                selectedPrice=data!!.productPrices [2].price
                priceDetails?.text=selectedPrice.toString()
            }
            R.id.vol4 ->{
                setBack()
                selectedId=data!!.productPrices [3]._id
                vol4.setBackgroundResource(R.drawable.round_btn_pls)
                vol4.text=data!!.productPrices [3].shortDescription
                Glide.with(context!!).load(base+data!!.productPrices [3].image).
                    into(storesSliderD)
                totPrice?.text= ((data!!.productPrices.get(3).price)*num).toString()
                selectedPrice=data!!.productPrices [3].price
                priceDetails?.text=selectedPrice.toString()
            }
            R.id.vol5 ->{
                setBack()
                selectedId=data!!.productPrices [4]._id
                vol5.setBackgroundResource(R.drawable.round_btn_pls)
                vol5.text=data!!.productPrices [4].shortDescription
                Glide.with(context!!).load(base+data!!.productPrices [4].image).
                    into(storesSliderD)
                totPrice?.text= ((data!!.productPrices.get(4).price)*num).toString()
                selectedPrice=data!!.productPrices [4].price
                priceDetails?.text=selectedPrice.toString()
            }
        }
    }
   fun findVolume(voulums: List<ProductPrice>){
       for(i in 0 until  voulums.size){
           if(i==0){
              vol1.visibility=View.VISIBLE
               vol1.setBackgroundResource(R.drawable.round_btn_pls)
               vol1.text=subStr(voulums[0].shortDescription)
        Glide.with(context!!).load(base+voulums[0].image).
                into(storesSliderD)

           }else if(i==1){
               vol2.visibility=View.VISIBLE
               vol2.setBackgroundResource(R.drawable.round_btn_black)
               vol2.text=subStr(voulums[1].shortDescription)
               Glide.with(context!!).load(base+voulums[1].image).
                   into(storesSliderD)
           }else if(i==2){
               vol3.visibility=View.VISIBLE
               vol3.setBackgroundResource(R.drawable.round_btn_black)
               vol3.text=subStr(voulums[2].shortDescription)
               Glide.with(context!!).load(base+voulums[2].image).
                   into(storesSliderD)
           }else if(i==3){
               vol4.visibility=View.VISIBLE
               vol4.setBackgroundResource(R.drawable.round_btn_black)
               vol4.text=subStr(voulums[3].shortDescription)
               Glide.with(context!!).load(base+voulums[3].image).
                   into(storesSliderD)
           }else if(i==4){
               vol5.visibility=View.VISIBLE
               vol5.setBackgroundResource(R.drawable.round_btn_black)
               vol5.text=subStr(voulums[4].shortDescription)
               Glide.with(context!!).load(base+voulums[4].image).
                   into(storesSliderD)
           }
       }
   }
    fun subStr(text:String):String{
        var textSub=text
        if(text.length>=10){
            textSub=text.subSequence(0,9).toString()+".."
        }
        return textSub
    }
}
