package com.mustafayusef.wakely.ui.compSection

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class SectionsViewModel(val repostary: SecRepostary) : ViewModel(){
    var Auth:SecLesener?=null
    fun getCtegore(token:String,compId:String){
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse=repostary.getCategore(token,compId)
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
