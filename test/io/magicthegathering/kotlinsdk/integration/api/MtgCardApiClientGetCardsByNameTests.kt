package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import org.junit.Test

class MtgCardApiClientGetCardsByNameTests {

    @Test
    fun getCardsByPartialNameNoResult() {
        val test = MtgCardApiClient.getCardsByPartialName("no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByPartialName() {
        val test = MtgCardApiClient.getCardsByPartialName("jace", 2, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards[0].name == "Jace Beleren" &&
                    cards[1].name == "Jace, Memory Adept"
        }
    }

    @Test
    fun getCardsByExactNameNoResult() {
        val test = MtgCardApiClient.getCardsByExactName("no_card", 10, 1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.isEmpty()
        }
    }

    @Test
    fun getCardsByExactName() {
        val test = MtgCardApiClient.getCardsByExactName("zurgo helmsmasher", 3, 1).test()

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
}