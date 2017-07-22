package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class MtgSetApiClientTests {

    @Test
    fun getAllSetsObservable() {
        val test = MtgSetApiClient.getAllSetsObservable().test()

        test.assertComplete()
        test.assertValue { sets ->
            sets.size > 1 &&
                    sets.find { it.code == "KTK" } != null
        }
    }

    @Test
    fun getAllSets() {
        val setsResponse: Response<List<MtgSet>> = MtgSetApiClient.getAllSets()
        val sets = setsResponse.body()

        Assert.assertTrue(setsResponse.isSuccessful)
        Assert.assertTrue(sets!!.size > 1 && sets.find { it.code == "KTK" } != null)
    }

    @Test
    fun getSpecificSetObservableAndGetA404NotFoundError() {
        val test = MtgSetApiClient.getSpecificSetObservable("KTK8").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun getSpecificSetObservable() {
        val test = MtgSetApiClient.getSpecificSetObservable("KTK").test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { set ->
            set.code == "KTK" &&
                    set.name == "Khans of Tarkir" &&
                    set.type == "expansion" &&
                    set.border == "black" &&
                    set.mkm_id == 1495 &&
                    set.mkm_name == "Khans of Tarkir" &&
                    set.releaseDate == DateTime.parse("2014-09-26", DateTimeFormatterSingleton.instance) &&
                    set.magicCardsInfoCode == "ktk" &&
                    set.block == "Khans of Tarkir"
        }
    }

    @Test
    fun getSpecificSetAndGetA404NotFoundError() {
        val setResponse: Response<MtgSet> = MtgSetApiClient.getSpecificSet("KTK8")

        Assert.assertFalse(setResponse.isSuccessful)
        Assert.assertEquals(404, setResponse.code())
    }

    @Test
    fun getSpecificSet() {
        val setResponse: Response<MtgSet> = MtgSetApiClient.getSpecificSet("KTK")
        val set = setResponse.body()

        Assert.assertTrue(setResponse.isSuccessful)
        Assert.assertEquals("KTK", set!!.code)
        Assert.assertEquals("Khans of Tarkir", set.name)
        Assert.assertEquals("expansion", set.type)
        Assert.assertEquals("black", set.border)
        Assert.assertEquals(1495, set.mkm_id)
        Assert.assertEquals("Khans of Tarkir", set.mkm_name)
        Assert.assertEquals(DateTime.parse("2014-09-26", DateTimeFormatterSingleton.instance), set.releaseDate)
        Assert.assertEquals("ktk", set.magicCardsInfoCode)
        Assert.assertEquals("Khans of Tarkir", set.block)
    }

    @Test
    fun generateBoosterPackBySetCodeObservableAndGetA404NotFoundError() {
        val test = MtgSetApiClient.generateBoosterPackBySetCodeObservable("mm").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun generateBoosterPackBySetCodeObservable() {
        val test = MtgSetApiClient.generateBoosterPackBySetCodeObservable("mm2").test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 15
        }
    }

    @Test
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        val boosterResponse = MtgSetApiClient.generateBoosterPackBySetCode("mm")

        Assert.assertFalse(boosterResponse.isSuccessful)
        Assert.assertEquals(404, boosterResponse.code())
    }

    @Test
    fun generateBoosterPackBySetCode() {
        val cardsResponse = MtgSetApiClient.generateBoosterPackBySetCode("mm2")
        val cards = cardsResponse.body()

        Assert.assertEquals(15, cards!!.size)
    }
}