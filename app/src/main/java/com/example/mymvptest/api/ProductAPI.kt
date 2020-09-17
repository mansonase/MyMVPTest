package com.example.mymvptest.api

import android.os.Handler


interface IProductAPI{
    interface LoadAPICallback{
        fun onGetResult(productResponse: ProductResponse)
    }
    fun getProduct(productId:String,loadAPICallback: LoadAPICallback)
}
class ProductAPI:IProductAPI {
    override fun getProduct(productId: String, loadAPICallback: IProductAPI.LoadAPICallback) {

        val handler=Handler()
        handler.postDelayed(Runnable {
            val productResponse=ProductResponse()
            productResponse.id="pixel3"
            productResponse.name="Google Pixel 3"
            productResponse.desc="5.5 inches screen"
            productResponse.price=27000
            loadAPICallback.onGetResult(productResponse)
        },1000)
    }
}