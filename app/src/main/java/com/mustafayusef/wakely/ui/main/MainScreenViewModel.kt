package com.mustafayusef.wakely.ui.main

import androidx.lifecycle.ViewModel;
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class MainScreenViewModel(val repostary: MainRepostary) : ViewModel() {

    var Auth: MainLesener?=null


//    fun GetMain(){
//        var CarsResponse:CarsModel?=null
//        var bannerResponse:banners?=null
//        Auth?.OnStart()
//
//        corurtins.main {
//            try {
//                CarsResponse=repostary.getCars()
//                CarsResponse ?.let {
//                    Auth?.onSucsess(it!!)
//                    CarsResponse=it!!
//                    //  Auth?.onComplete()
//                }
//                bannerResponse=repostary.getBanners()
//                bannerResponse ?.let {
//                    bannerResponse=it!!
//                    //  Auth?.onSucsessBanners(it!!)
//                }
//                Auth?.onComplete(CarsResponse!!, bannerResponse!!)
//
//            }catch (e: ApiExaptions){
//                e.message?.let { Auth?.onFailer(it) }
//
//            }catch (e: noInternetExeption){
//                e.message?.let { Auth?.onFailer(it) }
//            } catch (e: SocketTimeoutException){
//                e.message?.let { Auth?.onFailer(it) }
//            } catch (e: SocketException){
//                e.message?.let { Auth?.onFailer(it) }
//            }catch (e: ProtocolException){
//                e.message?.let { Auth?.onFailer(it) }
//            }
//
//        }
//
//    }
}
