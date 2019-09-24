package com.mustafayusef.holidaymaster.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}
fun View.snackbar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also {snackbar->
        snackbar.setAction("OK"){
           snackbar.dismiss()
        }
    }.show()

}
fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int { // For example columnWidthdp=180
    val displayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).toInt()
}