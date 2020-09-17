package com.example.mymvptest

import com.example.mymvptest.api.ProductResponse

class ProductContract {

    interface IProductPresenter {
        fun getProduct(productId:String)
        fun buy(productId: String,numbers:Int)
    }
    interface IProductView{
        fun onGetResult(productResponse: ProductResponse)
        fun onBuySuccess()
        fun onBuyFail()
    }
}