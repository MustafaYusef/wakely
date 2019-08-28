package com.mustafayusef.wakely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}
