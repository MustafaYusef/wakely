package com.mustafayusef.wakely.ui.Products

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.wakely.ui.Products.nationalProduct.NationalLesener
import com.mustafayusef.wakely.utils.corurtins
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class ProductesViewModel(val repostary: ProductRepostary) : ViewModel() {

    var AuthNational:NationalLesener?=null
    var Auth: productLesener? = null
    fun getProduct(token: String, SecId: String, pageNum: Int, Num: Int) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.getProduct(token, SecId, pageNum, Num)
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
            }

        }


    }

    fun AddToCart(
        token: String, productId: String, quantity: Int, companyId: String,
        priceId: String
    ) {
        Auth?.OnStart()
        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.AddToCart(
                    token, productId,
                    quantity, companyId, priceId
                )
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccessAdd(it1)
                }
            } catch (e: ApiExaptions) {
                e.message?.let {Auth?.onFailer(it)}

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


    fun getCart(token: String) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.getCart(token)
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccessGetCart(it1)
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

    fun sendOrder(token: String) {
        Auth?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.sendOrder(token)
                CarsDetailsResponse?.let { it1 ->
                    Auth?.OnSuccessSend(it1)
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

    fun national() {
        AuthNational?.OnStart()

        corurtins.main {
            try {
                val CarsDetailsResponse = repostary.National()
                CarsDetailsResponse?.let { it1 ->
                    AuthNational?.OnSuccess(it1)
                }
            } catch (e: ApiExaptions) {
                e.message?.let { AuthNational?.onFailer(it) }
            } catch (e: noInternetExeption) {
                e.message?.let { AuthNational?.onFailerNet(it) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { AuthNational?.onFailerNet(it) }
            } catch (e: SocketException) {
                e.message?.let { AuthNational?.onFailerNet(it) }
            } catch (e: ProtocolException) {
                e.message?.let { AuthNational?.onFailerNet(it) }
            }
        }
    }
}
