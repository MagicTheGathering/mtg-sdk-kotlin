package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import org.junit.Test
import retrofit2.HttpException

class MtgSetApiClientGenerateBoosterPackBySetCodeTests {

    @Test
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        val test = MtgSetApiClient.generateBoosterPackBySetCode("mm").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun generateBoosterPackBySetCode() {
        val test = MtgSetApiClient.generateBoosterPackBySetCode("mm2").test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 15
        }
    }
}