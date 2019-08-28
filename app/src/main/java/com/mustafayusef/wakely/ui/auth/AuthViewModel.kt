package com.mustafayusef.wakely.ui.auth

import androidx.lifecycle.ViewModel;
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class AuthViewModel(val repostary: AuthRepostary) : ViewModel() {
   var Auth:AuthLesener?=null
     fun Login(phone:String,password:String){
            Auth?.OnStart()

            corurtins.main {
                try {
                    val CarsDetailsResponse=repostary.Login(phone,password)
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


}
data class LoginBody(
   var phone: String,
    var password: String
)

