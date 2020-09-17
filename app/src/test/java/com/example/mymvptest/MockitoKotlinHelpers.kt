package com.example.mymvptest

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.internal.matchers.Any


class MockitoKotlinHelpers {

    fun <T> eq(obj:T):T= Mockito.eq<T>(obj)

    fun <T> any():T=Mockito.any<T>()

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>):T=argumentCaptor.capture()

    inline fun <reified T:Any> argumentCaptor()= ArgumentCaptor.forClass(T::class.java)

}