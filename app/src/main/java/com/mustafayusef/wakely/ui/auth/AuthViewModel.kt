package com.mustafayusef.wakely.ui.auth

import androidx.lifecycle.ViewModel;
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class AuthViewModel(val repostary: AuthRepostary) : ViewModel() {
   var Auth:AuthLesener?=null
     fun Login(phone:String,password:String,playerId:String){
            Auth?.OnStart()

            corurtins.main {
                try {
                    val CarsDetailsResponse=repostary.Login(phone,password,playerId)
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
                }catch (e: ConnectException){
                    e.message?.let { Auth?.onFailerNet(it) }
                }

            }


    }
    fun Profile(token:String){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.Profile(token)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessProfile(it1)
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
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }


    }

    fun Update(token:String,name:String,title:String,phone: String,image:MultipartBody.Part){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.Update(token,name,title,phone,image)
                CarsDetailsResponse ?.let {it1->

                    Auth?.OnSuccessUpdate(it1)
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
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }


    }
}
data class LoginBody(
   var phone: String,
    var password: String
)

