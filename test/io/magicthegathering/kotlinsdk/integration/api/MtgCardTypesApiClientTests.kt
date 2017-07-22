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
            cardTypes.size == 13 &&
                    cardTypes[0] == "Artifact" &&
                    cardTypes[1] == "Conspiracy" &&
                    cardTypes[2] == "Creature" &&
                    cardTypes[3] == "Enchantment" &&
                    cardTypes[4] == "Instant" &&
                    cardTypes[5] == "Land" &&
                    cardTypes[6] == "Phenomenon" &&
                    cardTypes[7] == "Plane" &&
                    cardTypes[8] == "Planeswalker" &&
                    cardTypes[9] == "Scheme" &&
                    cardTypes[10] == "Sorcery" &&
                    cardTypes[11] == "Tribal" &&
                    cardTypes[12] == "Vanguard"
        }
    }

    @Test
    fun getAllTypes() {
        val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllTypes()
        val cardTypes = cardTypesResponse.body()

        Assert.assertTrue(cardTypesResponse.isSuccessful)
        Assert.assertEquals(13, cardTypes!!.size)
        Assert.assertEquals("Artifact", cardTypes[0])
        Assert.assertEquals("Conspiracy", cardTypes[1])
        Assert.assertEquals("Creature", cardTypes[2])
        Assert.assertEquals("Enchantment", cardTypes[3])
        Assert.assertEquals("Instant", cardTypes[4])
        Assert.assertEquals("Land", cardTypes[5])
        Assert.assertEquals("Phenomenon", cardTypes[6])
        Assert.assertEquals("Plane", cardTypes[7])
        Assert.assertEquals("Planeswalker", cardTypes[8])
        Assert.assertEquals("Scheme", cardTypes[9])
        Assert.assertEquals("Sorcery", cardTypes[10])
        Assert.assertEquals("Tribal", cardTypes[11])
        Assert.assertEquals("Vanguard", cardTypes[12])
    }
}