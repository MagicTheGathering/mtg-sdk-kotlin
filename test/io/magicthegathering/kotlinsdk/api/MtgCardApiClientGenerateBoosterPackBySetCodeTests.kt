package io.magicthegathering.kotlinsdk.api

import org.junit.Assert
import org.junit.Test
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.onError

class MtgCardApiClientGenerateBoosterPackBySetCodeTests {

    @Test
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        MtgCardApiClient.generateBoosterPackBySetCode("mm")
                .onErrorResumeNext { exception: Throwable ->
                    Observable.empty()
                }
                .onError { exception: Throwable ->
                    Assert.assertTrue(exception is HttpException)
                    val httpException: HttpException = exception as HttpException
                    Assert.assertEquals(404, httpException.code())
                    Assert.assertEquals("Not Found", httpException.message())
                }
                .subscribe { }
    }

    @Test
    fun generateBoosterPackBySetCode() {
        MtgCardApiClient.generateBoosterPackBySetCode("mm2").subscribe { cards ->
            Assert.assertTrue(cards.size == 15)
            Assert.assertTrue(cards[0].rarity == "Mythic Rare" || cards[0].rarity == "Rare")
            Assert.assertEquals(cards[1].rarity, "Uncommon")
            Assert.assertEquals(cards[2].rarity, "Uncommon")
            Assert.assertEquals(cards[3].rarity, "Uncommon")
            Assert.assertEquals(cards[4].rarity, "Common")
            Assert.assertEquals(cards[5].rarity, "Common")
            Assert.assertEquals(cards[6].rarity, "Common")
            Assert.assertEquals(cards[7].rarity, "Common")
            Assert.assertEquals(cards[8].rarity, "Common")
            Assert.assertEquals(cards[9].rarity, "Common")
            Assert.assertEquals(cards[10].rarity, "Common")
            Assert.assertEquals(cards[11].rarity, "Common")
            Assert.assertEquals(cards[12].rarity, "Common")
            Assert.assertEquals(cards[13].rarity, "Common")
            Assert.assertEquals(cards[14].rarity, "Common")
        }
    }
}