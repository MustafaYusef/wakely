package com.mustafayusef.wakely.ui.Products.productDetails


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity
import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.Products.ProductRepostary
import com.mustafayusef.wakely.ui.Products.ProductViewModelFactory
import com.mustafayusef.wakely.ui.Products.ProductesViewModel
import com.mustafayusef.wakely.ui.Products.productLesener
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.progress.*


class productDetails : Fragment(),
    volumesAdapter.OnNoteLisener,productLesener {
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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= ProductRepostary(api!!)
        val factory= ProductViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(ProductesViewModel::class.java)
        viewModel?.Auth=this

        var data: productData =arguments!!.getSerializable("product") as productData
        ListVolum?.layoutManager=LinearLayoutManager(context)
        ListVolum?.adapter=
            volumesAdapter(
                context!!,
                this,
                data.productPrices
            )

        prodTitleD?.text=data.title
        priceDetails?.text=data.productPrices[0].price.toString()
        numItem?.text=num.toString()
        totPrice?.text= data.productPrices.get(0).price.toString()
        DetailsD?.text=data.description

        plusBtn?.setOnClickListener {
            num++
            numItem?.text=num.toString()
            totPrice?.text=(data.productPrices.get(0).price*num).toString()
        }
        MinusBtn?.setOnClickListener {
            if(num>1){
                num--
                numItem?.text=num.toString()
                totPrice?.text=(data.productPrices.get(0).price*num).toString()
            }

        }
        goToCart?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.cart)
        }



        val adapter = bannersAdapterDetails(context!!,data.productPrices)

        storesSliderD?.sliderAdapter = adapter

        //  context?.let { Glide.with(it).load(com.mustafayusef.sharay.R.drawable.car).into(carImageD) }
//          holder.view.storeSlider?.setIndicatorAnimation(IndicatorAnimations.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//          holder.view.storeSlider?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        storesSliderD?.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        storesSliderD?.indicatorSelectedColor = Color.WHITE
        storesSliderD?.indicatorUnselectedColor = Color.GRAY
        storesSliderD?.scrollTimeInSec = 3 //set scroll delay in seconds :
        storesSliderD?.startAutoCycle()

        AddBtnCart?.setOnClickListener {

            viewModel?.AddToCart(MainActivity.cacheObj.token,data._id,num,data.companyId,data.productPrices[0]._id)
        }

    }

}
