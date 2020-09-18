package com.example.mymvptest

import com.example.mymvptest.api.ProductResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ProductPresenterTest {

    private lateinit var presenter: ProductContract.IProductPresenter
    private var productResponse=ProductResponse()

    @Mock
    private lateinit var repository: IProductRepository
    @Mock
    private lateinit var productView:ProductContract.IProductView


    @Before
    fun setupPresenter(){
        MockitoAnnotations.initMocks(this)

        presenter=ProductPresenter(productView,repository)

        productResponse.id="pixel3"
        productResponse.name="Google Pixel 3"
        productResponse.price=27000
        productResponse.desc="Desc"
    }

    @Test
    fun getProductTest(){

        val productId="pixel3"

        presenter.getProduct(productId)

        val loadProductCallbackCaptor=argumentCaptor<IProductRepository.LoadProductCallback>()

        verify(repository).getProduct(eq(productResponse.id),capture(loadProductCallbackCaptor))

        loadProductCallbackCaptor.value.onProductResult(productResponse)

        verify(productView).onGetResult(productResponse)


    }
    @Test
    fun buySuccessTest(){
        val buyProductCallbackCaptor= argumentCaptor<IProductRepository.BuyProductCallback>()

        val productId="pixel3"
        val items=3

        presenter.buy(productId,items)

        verify(repository).buy(eq(productId), eq(items), capture(buyProductCallbackCaptor))

        buyProductCallbackCaptor.value.onBuyResult(true)
        verify(productView).onBuySuccess()

    }
    @Test
    fun buyFailTest(){
        val buyProductCallbackCaptor= argumentCaptor<IProductRepository.BuyProductCallback>()

        val productId="pixel3"
        val items=11

        presenter.buy(productId,items)

        verify(repository).buy(eq(productId), eq(items), capture(buyProductCallbackCaptor))

        buyProductCallbackCaptor.value.onBuyResult(false)

        verify(productView).onBuyFail()

    }
}