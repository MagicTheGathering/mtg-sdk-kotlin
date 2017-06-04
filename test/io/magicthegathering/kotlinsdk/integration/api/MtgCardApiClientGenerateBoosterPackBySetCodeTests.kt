package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import org.junit.Test
import retrofit2.HttpException

class MtgCardApiClientGenerateBoosterPackBySetCodeTests {

    @Test
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        val test = MtgCardApiClient.generateBoosterPackBySetCode("mm").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun generateBoosterPackBySetCode() {
        val test = MtgCardApiClient.generateBoosterPackBySetCode("mm2").test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 15
        }
    }
}