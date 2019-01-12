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
            cardTypes.size > 5
        }
    }

    @Test
    fun getAllSupertypes() {
        val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllSupertypes()
        val cardTypes = cardTypesResponse.body()

        Assert.assertTrue(cardTypesResponse.isSuccessful)
        Assert.assertTrue(cardTypes!!.size > 5)
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