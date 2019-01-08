package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgCardApiClientGetCardsByNameTests {

    @Test
    fun getCardsByPartialNameObservableNoResult() {
        val test = MtgCardApiClient.getCardsByPartialNameObservable("no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByPartialNameObservable() {
        val test = MtgCardApiClient.getCardsByPartialNameObservable("jace", 2, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 2
        }
    }

    @Test
    fun getCardsByPartialNameNoResult() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName("no_card", 10, 1)
        val cards = cardsResponse.body()

        Assert.assertTrue(cards!!.isEmpty())
    }

    @Test
    fun getCardsByPartialName() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName("jace", 2, 1)
        val cards = cardsResponse.body()

        Assert.assertFalse(cards!!.isEmpty())
        Assert.assertEquals(2, cards.size)
    }

    @Test
    fun getCardsByExactNameObservableNoResult() {
        val test = MtgCardApiClient.getCardsByExactNameObservable("no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByExactNameObservable() {
        val test = MtgCardApiClient.getCardsByExactNameObservable("zurgo helmsmasher", 3, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards[0].name == "Zurgo Helmsmasher" &&
                    cards[1].name == "Zurgo Helmsmasher" &&
                    cards[2].name == "Zurgo Helmsmasher"
        }
    }

    @Test
    fun getCardsByExactNameNoResult() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactName("no_card", 10, 1)
        val cards = cardsResponse.body()

        Assert.assertTrue(cards!!.isEmpty())
    }

    @Test
    fun getCardsByExactName() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactName("zurgo helmsmasher", 3, 1)
        val cards = cardsResponse.body()

        Assert.assertFalse(cards!!.isEmpty())
        Assert.assertEquals("Zurgo Helmsmasher", cards[0].name)
        Assert.assertEquals("Zurgo Helmsmasher", cards[1].name)
        Assert.assertEquals("Zurgo Helmsmasher", cards[2].name)
    }
}