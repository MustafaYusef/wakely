package com.mustafayusef.wakely.ui.compSection

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.singal.singelCompany
import com.mustafayusef.wakely.utils.corurtins
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class SectionsViewModel(val repostary: SecRepostary) : ViewModel(){
    var Auth:SecLesener?=null
    fun getCtegore(token:String,compId:String){
        Auth?.OnStart()
        var sectionRes: categoreResponse?=null
        var SingelRes: singelCompany?=null
        corurtins.main {
            try {
                sectionRes=repostary.getCategore(token,compId)
                sectionRes ?.let {

                    sectionRes=it!!
                    //  Auth?.onComplete()
                }
                SingelRes=repostary.getSingel(token,compId)
                SingelRes ?.let {
                    SingelRes=it!!
                    //  Auth?.onSucsessBanners(it!!)
                }


                    Auth?.OnSuccess(sectionRes!!, SingelRes!!)


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
    fun addRate(token:String,rate:Double,compId:String){
        Auth?.OnStart()

        corurtins.main {
            try {
               val sectionRes=repostary.addRate(token,rate,compId)
                sectionRes ?.let {

                    //  Auth?.onComplete()
                    Auth?.OnSuccessRate(it)
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


}
