package com.mustafayusef.wakely.ui.auth


import com.mustafayusef.wakely.MainActivity
import com.mustafayusef.wakely.data.AddUserRes
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.provRes
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepostary(val api: myApi): SafeApiRequest(){
    suspend fun Login(phone:String,password:String):loginResponse{
        var log=LoginBody(phone=phone,password = password)
        return SafeRequest{
            api.Login(log)
        }}
    suspend fun getCategore(token:String,compId:String):categoreResponse{

        return SafeRequest{
            api.GetCategore(token,compId)
        }}
    suspend fun AddUser(name:String,password:String,phone:String,role:Int):AddUserRes{
        var body=addUserBody(name=name,password = password,phone = phone,role =1)

        return SafeRequest{
            api.AddUser(MainActivity.cacheObj.token,body)
        }}

    suspend fun AddShops(title:String, phone:String, provinceId:String
                         , cityId:String, nearLocation:String,
                         imagesBodyRequest: MultipartBody.Part, id:String):AddUserRes{
        var loc=location(provinceId=RequestBody.create(MediaType.parse("text/plain"),provinceId)
            ,cityId = RequestBody.create(MediaType.parse("text/plain"),cityId),
            nearLocation = RequestBody.create(MediaType.parse("text/plain"),nearLocation))
        var tit=RequestBody.create(MediaType.parse("text/plain"),title)
        var phon=RequestBody.create(MediaType.parse("text/plain"),phone)
        var id1=RequestBody.create(MediaType.parse("text/plain"),id)
        return SafeRequest{
            api.AddShops(MainActivity.cacheObj.token, tit,phon,loc,imagesBodyRequest,id1)
        }}

    suspend fun GetProv():provRes{
        return SafeRequest{
            api.GetProv()
        }}

    suspend fun GetCity(id:String):provRes{
        return SafeRequest{
            api.GetCity(id)
        }}
}
data class addUserBody(
    var name:String,
    var password:String,
    var phone:String,
    var role:Int

)
data class location(
    var provinceId: RequestBody,
    var cityId:RequestBody,
    var nearLocation:RequestBody


)
