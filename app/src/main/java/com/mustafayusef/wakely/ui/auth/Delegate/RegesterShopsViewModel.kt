package com.mustafayusef.wakely.ui.auth.Delegate

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.MultipartBody
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class RegesterShopsViewModel(val repostary: AuthRepostary) : ViewModel() {
    var Auth: DelegateLesener?=null

   fun AddUser(token:String,name:String,password:String,phone:String,role:Int){
       Auth?.OnStart()

       corurtins.main {
           try {
               val CarsDetailsResponse=repostary.AddUser(token,name,password,phone,role)
               CarsDetailsResponse ?.let {it1->

                   Auth?.OnSuccess(it1)
               }

           }catch (e: ApiExaptions){
               e.message?.let { Auth?.onFailer(it) }

           }catch (e: noInternetExeption){
               e.message?.let { Auth?.onFailerNet(it) }
           }catch (e: SocketTimeoutException){
               e.message?.let { Auth?.onFailerNet(it) }}
           catch (e: SocketException){
               e.message?.let { Auth?.onFailerNet(it) }
           }catch (e: ProtocolException){
               e.message?.let { Auth?.onFailerNet(it) }
           }

       }
   }

    fun AddShops(token:String,title:String,phone:String,provinceId:String
                 ,cityId:String,nearLocation:String,imagesBodyRequest:MultipartBody.Part,id:String){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.AddShops(token,title,phone,provinceId
                    ,cityId,nearLocation,imagesBodyRequest,id)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccess(it1)
                }

            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }
    }

    fun GetProv(){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.GetProv()
                CarsDetailsResponse ?.let {it1->
                    println("resssssssssssssssss  "+it1)
                    Auth?.OnSuccessProv(it1)
                }
                println("resssssssssssssssss  "+CarsDetailsResponse)
            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }
    }
    fun GetCity(id:String){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.GetCity(id)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessCity(it1)
                }

            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }}
            catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }
    }
}
