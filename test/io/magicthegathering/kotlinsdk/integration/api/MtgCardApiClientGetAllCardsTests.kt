package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import org.junit.Test

class MtgCardApiClientGetAllCardsTests {

    @Test
    fun getAllCards() {
        val test = MtgCardApiClient.getAllCards().test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 10
        }
    }

    @Test
    fun getAllCardsWithPageSize() {
        val test = MtgCardApiClient.getAllCards(50).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 50
        }
    }
}