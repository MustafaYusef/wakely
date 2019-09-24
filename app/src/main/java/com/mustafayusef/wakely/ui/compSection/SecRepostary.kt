package com.mustafayusef.wakely.ui.compSection


import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.data.singal.singelCompany
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class SecRepostary(val api: myApi): SafeApiRequest(){

    suspend fun getCategore(token:String,compId:String):categoreResponse{

        return SafeRequest{
            api.GetCategore(token,compId)
        }}

    suspend fun getProduct(token:String,SecId:String,pageNum:Int,Num:Int): productsResponse {

        return SafeRequest{
            api.getProduct(token,SecId,pageNum,Num)
        }}
    suspend fun getSingel(token:String,CompId:String): singelCompany {

        return SafeRequest{
            api.getSingel(token,CompId)
        }}

    suspend fun addRate(token:String,rate:Double,CompId:String): acceptRes {

        return SafeRequest{
            api.ratting(token,rate,CompId)
        }}
}