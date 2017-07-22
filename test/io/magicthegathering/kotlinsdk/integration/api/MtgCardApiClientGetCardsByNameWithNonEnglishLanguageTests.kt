package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgCardApiClientGetCardsByNameWithNonEnglishLanguageTests {

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguageObservableNoResult() {
        val test = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguageObservable("spanish", "no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguageObservable() {
        val test = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguageObservable("spanish", "jace", 2, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards[0].name == "Jace Beleren" &&
                    cards[1].name == "Jace, Memory Adept"
        }
    }

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguageNoResult() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage("spanish", "no_card", 10, 1)
        val cards = cardsResponse.body()

        Assert.assertTrue(cards!!.isEmpty())
    }

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguage() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage("spanish", "jace", 2, 1)
        val cards = cardsResponse.body()

        Assert.assertFalse(cards!!.isEmpty())
        Assert.assertEquals("Jace Beleren", cards[0].name)
        Assert.assertEquals("Jace, Memory Adept", cards[1].name)
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguageObservableNoResult() {
        val test = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguageObservable("spanish", "no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguageObservable() {
        val test = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguageObservable("spanish", "zurgo aplastacráneos", 3, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards[0].name == "Zurgo Helmsmasher" &&
                    cards[0].rarity == "Special" &&
                    cards[0].set == "pPRE" &&
                    cards[0].setName == "Prerelease Events" &&
                    cards[1].name == "Zurgo Helmsmasher" &&
                    cards[1].rarity == "Mythic Rare" &&
                    cards[1].set == "KTK" &&
                    cards[1].setName == "Khans of Tarkir"
        }
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguageNoResult() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage("spanish", "no_card", 10, 1)
        val cards = cardsResponse.body()

        Assert.assertTrue(cards!!.isEmpty())
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguage() {
        val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage("spanish", "zurgo aplastacráneos", 3, 1)
        val cards = cardsResponse.body()

        Assert.assertFalse(cards!!.isEmpty())
        Assert.assertEquals("Zurgo Helmsmasher", cards[0].name)
        Assert.assertEquals("Special", cards[0].rarity)
        Assert.assertEquals("pPRE", cards[0].set)
        Assert.assertEquals("Prerelease Events", cards[0].setName)
        Assert.assertEquals("Zurgo Helmsmasher", cards[1].name)
        Assert.assertEquals("Mythic Rare", cards[1].rarity)
        Assert.assertEquals("KTK", cards[1].set)
    }
}