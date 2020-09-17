package com.example.mymvptest


import android.util.Log
import com.example.mymvptest.api.IProductAPI
import com.example.mymvptest.api.ProductAPI
import com.example.mymvptest.api.ProductResponse


interface IProductRepository{
    fun getProduct(productId:String,loadProductCallback:LoadProductCallback)
    fun buy(id:String,items:Int,callback: BuyProductCallback)

    interface LoadProductCallback{
        fun onProductResult(productResponse: ProductResponse)
    }
    interface BuyProductCallback {
        fun onBuyResult(isSuccess:Boolean)
    }
}
class ProductRepository(private val productAPI: ProductAPI):IProductRepository {


    override fun getProduct(productId: String, loadProductCallback: IProductRepository.LoadProductCallback) {

        productAPI.getProduct(productId, object : IProductAPI.LoadAPICallback {
            override fun onGetResult(productResponse: ProductResponse) {
                loadProductCallback.onProductResult(productResponse)
            }
        })
    }

    override fun buy(id: String, items: Int, callback: IProductRepository.BuyProductCallback) {


        Log.d("repository","buy items? "+items)
        if (items<=10){
            callback.onBuyResult(true)
        }else{
            callback.onBuyResult(false)
        }
    }
}