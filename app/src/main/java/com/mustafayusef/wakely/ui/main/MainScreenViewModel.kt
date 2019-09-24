package com.mustafayusef.wakely.ui.main

import androidx.lifecycle.ViewModel;
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.data.BannersResponse
import com.mustafayusef.wakely.data.ShopsResponse
import com.mustafayusef.wakely.data.disscountCompany
import com.mustafayusef.wakely.utils.corurtins
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class MainScreenViewModel(val repostary: MainRepostary) : ViewModel() {

    var Auth: MainLesener?=null


    fun GetMain(){
        var ShopsResponse:ShopsResponse?=null
        var CompanyResponse:ShopsResponse?=null
        var bannerResponse:BannersResponse?=null
        var DisscountResponse:disscountCompany?=null
        Auth?.OnStart()


        corurtins.main {
            try {
                bannerResponse=repostary.getBanners()
                bannerResponse ?.let {

                    bannerResponse=it!!
                    //  Auth?.onComplete()
                }
                ShopsResponse=repostary.getShops(0,7)
                ShopsResponse ?.let {
                    ShopsResponse=it!!
                    //  Auth?.onSucsessBanners(it!!)
                }
                CompanyResponse=repostary.getCompany(0,7)
                CompanyResponse ?.let {
                    CompanyResponse=it!!
                    //  Auth?.onSucsessBanners(it!!)
                }
                DisscountResponse=repostary.getDisscountComp()
                DisscountResponse ?.let {
                    DisscountResponse=it!!
                    //  Auth?.onSucsessBanners(it!!)
                }
                Auth?.OnSuccess(bannerResponse!!,ShopsResponse!!,CompanyResponse!!,DisscountResponse!!)

            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }

    }

    fun GetAllShops(){
        Auth?.OnStart()

        corurtins.main {
            try {
              var  ShopsResponse=repostary.getShops(0,0)
                ShopsResponse ?.let {

                    Auth?.onSuccessAll(ShopsResponse!!)
                }

            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }

    }

    fun GetAllCompany(){
        Auth?.OnStart()

        corurtins.main {
            try {
                var  ShopsResponse=repostary.getCompany(0,0)
                ShopsResponse ?.let {

                    Auth?.onSuccessAllComp(ShopsResponse!!)
                }

            }catch (e: ApiExaptions){
                e.message?.let { Auth?.onFailer(it) }

            }catch (e: noInternetExeption){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketTimeoutException){
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ProtocolException){
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }

        }

    }

}
