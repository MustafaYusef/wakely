package com.mustafayusef.wakely.ui.Products

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.mustafayusef.wakely.R
import kotlinx.android.synthetic.main.productes_fragment.*

class productes : Fragment() {

    companion object {
        fun newInstance() = productes()
    }

    private lateinit var viewModel: ProductesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.productes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductesViewModel::class.java)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.productes) {
               // bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
              //  bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }
        }

        productList?.layoutManager=LinearLayoutManager(context!!)
        productList?.adapter=productsAdapter(context!!)
    }

}
