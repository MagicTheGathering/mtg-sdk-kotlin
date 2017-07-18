package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import org.joda.time.DateTime
import org.junit.Test
import retrofit2.HttpException

class MtgSetApiClientTests {

    @Test
    fun getAllSets() {
        val test = MtgSetApiClient.getAllSets().test()

        test.assertComplete()
        test.assertValue { sets ->
            sets.size > 1 &&
                    sets.find { it.code == "KTK" } != null
        }
    }

    @Test
    fun getSpecificSetAndGetA404NotFoundError() {
        val test = MtgSetApiClient.getSpecificSet("KTK8").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun getSpecificSet() {
        val test = MtgSetApiClient.getSpecificSet("KTK").test()

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
    fun generateBoosterPackBySetCodeAndGetA404NotFoundError() {
        val test = MtgSetApiClient.generateBoosterPackBySetCode("mm").test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun generateBoosterPackBySetCode() {
        val test = MtgSetApiClient.generateBoosterPackBySetCode("mm2").test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { cards ->
            cards.size == 15
        }
    }
}