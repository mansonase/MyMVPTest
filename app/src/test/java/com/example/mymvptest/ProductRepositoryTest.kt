package com.example.mymvptest

import com.example.mymvptest.api.IProductAPI
import com.example.mymvptest.api.ProductResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductRepositoryTest {

    private lateinit var repository: IProductRepository
    private var productResponse=ProductResponse()

    @Mock
    private lateinit var productAPI: IProductAPI

    @Mock
    private lateinit var repositoryCallback:IProductRepository.LoadProductCallback

    @Before
    fun setupPresenter(){

        MockitoAnnotations.initMocks(this)
        repository=ProductRepository(productAPI)

        productResponse.id="pixel3"
        productResponse.name="Google Pixel 3"
        productResponse.price=27000
        productResponse.desc="DESC"

    }

    @Test
    fun getProductTest(){
        val productId="pixel3"
        repository.getProduct(productId,repositoryCallback)

        val productAPICallbackCaptor= argumentCaptor<IProductAPI.LoadAPICallback>()
        Mockito.verify<IProductAPI>(productAPI).getProduct(any(), capture(productAPICallbackCaptor))

        productAPICallbackCaptor.value.onGetResult(productResponse)

        Mockito.verify(repositoryCallback).onProductResult(productResponse)

    }
}