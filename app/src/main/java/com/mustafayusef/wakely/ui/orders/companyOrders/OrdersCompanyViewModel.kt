package com.mustafayusef.wakely.ui.orders.companyOrders

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class OrdersCompanyViewModel(val repostary: OrdersCompanyRepostary) : ViewModel() {
    var Auth: OrdersCompanyLesener?=null

    fun getOrdersCompany(token: String) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.GetOrdersCompany(token)
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccess(it1)
                }
            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            } catch (e: noInternetExeption) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketException) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: ProtocolException) {
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e: ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }
        }
    }

    fun Accept(token:String, id:String,
               status:Int,
               notes:String) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.Accept(token,id, status, notes)
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccessAccept(it1)
                }
            } catch (e: ApiExaptions) {
                e.message?.let { Auth?.onFailer(it) }
            } catch (e: noInternetExeption) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: SocketException) {
                e.message?.let { Auth?.onFailerNet(it) }
            } catch (e: ProtocolException) {
                e.message?.let { Auth?.onFailerNet(it) }
            }catch (e:ConnectException){
                e.message?.let { Auth?.onFailerNet(it) }
            }
        }
    }

}
