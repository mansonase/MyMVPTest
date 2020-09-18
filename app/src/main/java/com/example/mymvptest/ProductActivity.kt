package com.example.mymvptest


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mymvptest.api.ProductAPI
import com.example.mymvptest.api.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat


class ProductActivity: AppCompatActivity(),ProductContract.IProductView,View.OnClickListener {

    private val productId="pixel3"

    private lateinit var productRepository:ProductRepository
    private lateinit var productPresenter:ProductPresenter

    private var priceSingle=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buy.setOnClickListener(this)

        productRepository=ProductRepository(ProductAPI())
        productPresenter=ProductPresenter(this,productRepository)
        productPresenter.getProduct(productId)
    }

    override fun onGetResult(productResponse: ProductResponse) {
        productName.text=productResponse.name
        productDesc.text=productResponse.desc

        val currencyFormat=NumberFormat.getCurrencyInstance()
        currencyFormat.maximumFractionDigits=0
        priceSingle=productResponse.price

        val price=currencyFormat.format(priceSingle)

        productPrice.text=price

    }

    override fun onBuySuccess() {


        val priceTotal=priceSingle*(productItems.text.toString().toInt())
        totalPrice.text= priceTotal.toString()

        Toast.makeText(this,"Congrats!!!",Toast.LENGTH_SHORT).show()
    }

    override fun onBuyFail() {

        val builder=AlertDialog.Builder(this)
        builder.setMessage("Failed").setTitle("Error")
        builder.show()

        //Toast.makeText(this,"you can't buy more than 10 items, you can't! ",Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {

        when(v?.id){
            R.id.buy->{
                val numbers=productItems.text.toString().toInt()
                productPresenter.buy(productId, numbers)
                Log.d("onActivity","click "+numbers)
            }
            else->{

            }
        }

    }
}