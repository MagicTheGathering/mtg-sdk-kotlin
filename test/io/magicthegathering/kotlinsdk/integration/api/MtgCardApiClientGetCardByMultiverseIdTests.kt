package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import org.junit.Test
import retrofit2.HttpException

class MtgCardApiClientGetCardByMultiverseIdTests {

    @Test
    fun getSingleCardAndGetA404NotFoundError() {
        val test = MtgCardApiClient.getCard(-1).test()

        test.assertError { error ->
            val httpException = error as HttpException
            httpException.code() == 404
        }
    }

    @Test
    fun getNormalCard() {
        val test = MtgCardApiClient.getCard(1).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Ankh of Mishra" &&
                    card.manaCost == "{2}" &&
                    card.cmc == 2
        }
    }

    @Test
    fun getFlipCard() {
        val test = MtgCardApiClient.getCard(78691).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Student of Elements" &&
                    card.manaCost == "{1}{U}" &&
                    card.cmc == 2
        }
    }

    @Test
    fun getDoubleFacedCard() {
        val test = MtgCardApiClient.getCard(409741).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Archangel Avacyn" &&
                    card.manaCost == "{3}{W}{W}" &&
                    card.cmc == 5
        }
    }

    @Test
    fun getSplitCard() {
        val test = MtgCardApiClient.getCard(20578).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Pain" &&
                    card.manaCost == "{B}" &&
                    card.cmc == 1
        }
    }

    @Test
    fun getTokenCard() {
        val test = MtgCardApiClient.getCard(159048).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Elemental"
        }
    }

    @Test
    fun getPlaneCard() {
        val test = MtgCardApiClient.getCard(198073).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Academy at Tolaria West"
        }
    }

    @Test
    fun getSchemeCard() {
        val test = MtgCardApiClient.getCard(212648).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "All in Good Time"
        }
    }

    @Test
    fun getPhenomenonCard() {
        val test = MtgCardApiClient.getCard(226509).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Chaotic Aether"
        }
    }

    @Test
    fun getLevelerCard() {
        val test = MtgCardApiClient.getCard(194918).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Caravan Escort" &&
                    card.manaCost == "{W}" &&
                    card.cmc == 1
        }
    }

    @Test
    fun getVanguardCard() {
        val test = MtgCardApiClient.getCard(12329).test()

        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue { card ->
            card.name == "Ashnod"
        }
    }
}