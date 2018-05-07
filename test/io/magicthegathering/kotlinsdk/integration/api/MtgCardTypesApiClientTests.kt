package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgCardTypesApiClient
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgCardTypesApiClientTests {

    @Test
    fun getAllTypesObservable() {
        val test = MtgCardTypesApiClient.getAllTypesObservable().test()

        test.assertComplete()
        test.assertValue { cardTypes ->
            cardTypes.size > 10
        }
    }

    @Test
    fun getAllTypes() {
        val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllTypes()
        val cardTypes = cardTypesResponse.body()

        Assert.assertTrue(cardTypesResponse.isSuccessful)
        Assert.assertTrue(10 < cardTypes!!.size)
    }

    @Test
    fun getAllSupertypesObservable() {
        val test = MtgCardTypesApiClient.getAllSupertypesObservable().test()

        test.assertComplete()
        test.assertValue { cardTypes ->
            cardTypes.size == 5 &&
                    cardTypes[0] == "Basic" &&
                    cardTypes[1] == "Legendary" &&
                    cardTypes[2] == "Ongoing" &&
                    cardTypes[3] == "Snow" &&
                    cardTypes[4] == "World"
        }
    }

    @Test
    fun getAllSupertypes() {
        val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllSupertypes()
        val cardTypes = cardTypesResponse.body()

        Assert.assertTrue(cardTypesResponse.isSuccessful)
        Assert.assertEquals(5, cardTypes!!.size)
        Assert.assertEquals("Basic", cardTypes[0])
        Assert.assertEquals("Legendary", cardTypes[1])
        Assert.assertEquals("Ongoing", cardTypes[2])
        Assert.assertEquals("Snow", cardTypes[3])
        Assert.assertEquals("World", cardTypes[4])
    }

    @Test
    fun getAllSubtypesObservable() {
        val test = MtgCardTypesApiClient.getAllSubtypesObservable().test()

        test.assertComplete()
        test.assertValue { cardTypes -> cardTypes.size > 8 }
    }

    @Test
    fun getAllSubtypes() {
        val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllSubtypes()
        val cardTypes = cardTypesResponse.body()

        Assert.assertTrue(cardTypesResponse.isSuccessful)
        Assert.assertTrue(8 < cardTypes!!.size)
    }
}