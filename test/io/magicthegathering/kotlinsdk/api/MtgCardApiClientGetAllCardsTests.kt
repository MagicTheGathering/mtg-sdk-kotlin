package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import org.junit.Assert
import org.junit.Test

class MtgCardApiClientGetAllCardsTests {

    @Test
    fun getAllCards() {
        MtgCardApiClient.getAllCards().subscribe { cards: List<MtgCard> ->
            Assert.assertEquals(10, cards.size)
        }
    }

    @Test
    fun getAllCardsWithPageSize() {
        MtgCardApiClient.getAllCards(50).subscribe { cards: List<MtgCard> ->
            Assert.assertEquals(50, cards.size)
        }
    }
}