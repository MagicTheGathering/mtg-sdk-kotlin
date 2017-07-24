package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class MtgCardApiClientGetCardByMultiverseIdTests {

    @Test
    fun getCardObservableAndGetA404NotFoundError() {
        val test = MtgCardApiClient.getCardObservable(-1).test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun getCardObservable() {
        val test = MtgCardApiClient.getCardObservable(1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Ankh of Mishra" &&
                    card.manaCost == "{2}" &&
                    card.cmc == 2
        }
    }

    @Test
    fun getCardAndGetA404NotFoundError() {
        val cardResponse: Response<MtgCard> = MtgCardApiClient.getCard(-1)

        Assert.assertFalse(cardResponse.isSuccessful)
        Assert.assertEquals(404, cardResponse.code())
    }

    @Test
    fun getCard() {
        val cardResponse: Response<MtgCard> = MtgCardApiClient.getCard(1)
        val card = cardResponse.body()

        Assert.assertTrue(cardResponse.isSuccessful)
        Assert.assertEquals("Ankh of Mishra", card!!.name)
        Assert.assertEquals("{2}", card.manaCost)
        Assert.assertEquals(2, card.cmc)
    }
}