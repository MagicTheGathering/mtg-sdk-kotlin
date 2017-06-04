package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import org.junit.Test

class MtgCardApiClientGetCardsByNameWithNonEnglishLanguageTests {

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguageNoResult() {
        val test = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage("spanish", "no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByPartialNameWithNonEnglishLanguage() {
        val test = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage("spanish", "jace", 2, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards[0].name == "Jace Beleren" &&
                    cards[1].name == "Jace, Memory Adept"
        }
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguageNoResult() {
        val test = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage("spanish", "no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByExactNameWithNonEnglishLanguage() {
        val test = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage("spanish", "zurgo aplastacráneos", 3, 1).test()

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
}