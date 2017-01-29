package io.magicthegathering.kotlinsdk.api

import org.junit.Assert
import org.junit.Test
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.onError

class MtgCardApiClientGenerateBoosterPackBySetCodeTests {

    @Test
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        MtgCardApiClient.generateBoosterPackBySetCode("mm")
                .onErrorResumeNext { exception: Throwable ->
                    Observable.empty()
                }
                .onError { exception: Throwable ->
                    Assert.assertTrue(exception is HttpException)
                    val httpException: HttpException = exception as HttpException
                    Assert.assertEquals(404, httpException.code())
                    Assert.assertEquals("Not Found", httpException.message())
                }
                .subscribe { }
    }

    @Test
    fun generateBoosterPackBySetCode() {
        MtgCardApiClient.generateBoosterPackBySetCode("mm2").subscribe { cards ->
            Assert.assertTrue(cards.size == 15)
        }
    }
}