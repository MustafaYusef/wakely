package com.mustafayusef.wakely.ui.orders

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.utils.corurtins
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class OrdersViewModel(var repostary: OrdersRepostary) : ViewModel() {
   var Auth:OrdersLesener?=null
    fun getOrdersHistory(token: String) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.GetOrdersHistory(token)
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccessGetOrders(it1)
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
            }
        }
    }
}
