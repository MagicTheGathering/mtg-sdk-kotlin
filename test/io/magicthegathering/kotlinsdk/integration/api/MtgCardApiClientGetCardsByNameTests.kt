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
            cards[0].name == "Jace Beleren" &&
                    cards[1].name == "Jace, Memory Adept"
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
        Assert.assertEquals("Jace Beleren", cards[0].name)
        Assert.assertEquals("Jace, Memory Adept", cards[1].name)
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
                    cards[0].rarity == "Special" &&
                    cards[0].set == "pPRE" &&
                    cards[0].setName == "Prerelease Events" &&
                    cards[1].name == "Zurgo Helmsmasher" &&
                    cards[1].rarity == "Mythic Rare" &&
                    cards[1].set == "DDN" &&
                    cards[1].setName == "Duel Decks: Speed vs. Cunning" &&
                    cards[2].name == "Zurgo Helmsmasher" &&
                    cards[2].rarity == "Mythic Rare" &&
                    cards[2].set == "KTK" &&
                    cards[2].setName == "Khans of Tarkir"
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
        Assert.assertEquals("Special", cards[0].rarity)
        Assert.assertEquals("pPRE", cards[0].set)
        Assert.assertEquals("Prerelease Events", cards[0].setName)
        Assert.assertEquals("Zurgo Helmsmasher", cards[1].name)
        Assert.assertEquals("Mythic Rare", cards[1].rarity)
        Assert.assertEquals("DDN", cards[1].set)
        Assert.assertEquals("Duel Decks: Speed vs. Cunning", cards[1].setName)
        Assert.assertEquals("Zurgo Helmsmasher", cards[2].name)
        Assert.assertEquals("Mythic Rare", cards[2].rarity)
        Assert.assertEquals("KTK", cards[2].set)
        Assert.assertEquals("Khans of Tarkir", cards[2].setName)
    }
}