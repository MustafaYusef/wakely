package com.mustafayusef.wakely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    object cacheObj : KotprefModel() {

        var token by stringPref("")
        var role by intPref(0)
    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Kotpref.init(this)
        navController= Navigation.findNavController(this,R.id.navHost)
        bottomNav.setupWithNavController(navController)

//        val badgeCount = 4
//        ShortcutBadger.applyCount(this, badgeCount) //for 1.1.4+
//       // ShortcutBadger.with(applicationContext).count(badgeCount)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(navController.currentDestination?.id==R.id.cart){
                orders_fragmentBtn?.visibility=View.VISIBLE
            }else{
                orders_fragmentBtn?.visibility=View.GONE
            }
        }
        if(MainActivity.cacheObj.role==2){
            bottomNav.menu.get(1).setIcon(R.drawable.ic_assignment_black_24dp)
            bottomNav.menu.get(1).setTitle("الطلبات")

        }else{
            bottomNav.menu.get(1).setIcon(R.drawable.ic_shopping_cart_black_24dp)
            bottomNav.menu.get(1).setTitle("السلة")
        }
        navController.addOnDestinationChangedListener {controller, destination, arguments ->
            if(MainActivity.cacheObj.role==2){
                bottomNav.menu.get(1).setIcon(R.drawable.ic_assignment_black_24dp)
                bottomNav.menu.get(1).setTitle("الطلبات")

            }else{
                bottomNav.menu.get(1).setIcon(R.drawable.ic_shopping_cart_black_24dp)
                bottomNav.menu.get(1).setTitle("السلة")
            }
        }


        orders_fragmentBtn?.setOnClickListener {
            navController?.navigate(R.id.orders_fragment)
        }

//       val itemComp= this.findViewById<View>(R.id.ordersCompany)
//        val item= this.findViewById<View>(R.id.orders_fragment)
//        if(cacheObj.role!=2){
//            item.visibility=View.VISIBLE
//            itemComp.visibility=View.GONE
//        }else{
//            item.visibility=View.GONE
//            itemComp.visibility=View.VISIBLE
//        }
    }

    override fun onBackPressed() {

        if (navController.currentDestination?.id == R.id.login||
            navController.currentDestination?.id==R.id.main_fragment||
            navController.currentDestination?.id==R.id.regesterShops) {
            finish()

            // do nothing
        } else if(navController.currentDestination?.id==R.id.productesNoSec){
            navController.navigate(R.id.main_fragment)
        }
        else {
            super.onBackPressed()
        }
    }
}
