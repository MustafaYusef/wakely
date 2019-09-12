package com.mustafayusef.wakely.ui.main


import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class MainRepostary(val api: myApi): SafeApiRequest(){

    suspend fun getCategore(token:String,compId:String):categoreResponse{

        return SafeRequest{
            api.GetCategore(token,compId)
        }}
    suspend fun getBanners():BannersResponse{

        return SafeRequest{
            api.getBanners()
        }}
    suspend fun getShops(pageNum:Int,num:Int):ShopsResponse{

        return SafeRequest{
            api.getShops(pageNum,num)
        }}
    suspend fun getCompany(pageNum:Int,num:Int):ShopsResponse{

        return SafeRequest{
            api.getCompany(pageNum,num)
        }}
    suspend fun getDisscountComp():disscountCompany{

        return SafeRequest{
            api.getDisscountComp()
        }}

}