package com.mustafayusef.wakely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import kotlinx.android.synthetic.main.activity_main.*
import me.leolin.shortcutbadger.ShortcutBadger
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



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

        val badgeCount = 4
        ShortcutBadger.applyCount(this, badgeCount) //for 1.1.4+
       // ShortcutBadger.with(applicationContext).count(badgeCount)
    }
}
