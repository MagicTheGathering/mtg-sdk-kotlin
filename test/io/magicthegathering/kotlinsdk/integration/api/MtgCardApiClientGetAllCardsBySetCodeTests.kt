package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgCardApiClientGetAllCardsBySetCodeTests {

    companion object {
        private const val SET_CODE = "DOM"
    }

    @Test
    fun getAllCardsBySetCodeObservable() {
        val test = MtgCardApiClient.getAllCardsBySetCodeObservable(SET_CODE).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 10 && cards.count { it.set == SET_CODE } == 10
        }
    }

    @Test
    fun getAllCardsBySetCodeObservableWithPageSize() {
        val test = MtgCardApiClient.getAllCardsBySetCodeObservable(SET_CODE, 50).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 50 && cards.count { it.set == SET_CODE } == 50
        }
    }

    @Test
    fun getAllCardsBySetCode() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCode(SET_CODE)
        val cards = cardsResponse.body()

        Assert.assertTrue(cardsResponse.isSuccessful)
        Assert.assertEquals(10, cards!!.size)
        Assert.assertEquals(10, cards.count { it.set == SET_CODE })
    }

    @Test
    fun getAllCardsBySetCodeWithPageSize() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCode(SET_CODE, 50)
        val cards = cardsResponse.body()

        Assert.assertTrue(cardsResponse.isSuccessful)
        Assert.assertEquals(50, cards!!.size)
        Assert.assertEquals(50, cards.count { it.set == SET_CODE })
    }
}