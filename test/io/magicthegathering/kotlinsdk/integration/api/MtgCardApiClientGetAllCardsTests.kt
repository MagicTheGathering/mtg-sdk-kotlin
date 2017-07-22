package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgCardApiClientGetAllCardsTests {

    @Test
    fun getAllCardsObservable() {
        val test = MtgCardApiClient.getAllCardsObservable().test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 10
        }
    }

    @Test
    fun getAllCardsObservableWithPageSize() {
        val test = MtgCardApiClient.getAllCardsObservable(50).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 50
        }
    }

    @Test
    fun getAllCards() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCards()
        val cards = cardsResponse.body()

        Assert.assertTrue(cardsResponse.isSuccessful)
        Assert.assertEquals(10, cards!!.size)
    }

    @Test
    fun getAllCardsWithPageSize() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCards(50)
        val cards = cardsResponse.body()

        Assert.assertTrue(cardsResponse.isSuccessful)
        Assert.assertEquals(50, cards!!.size)
    }
}