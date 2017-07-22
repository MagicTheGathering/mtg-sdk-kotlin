package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgFormatsApiClient
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class MtgFormatsApiClientTests {

    @Test
    fun getAllFormatsObservable() {
        val test = MtgFormatsApiClient.getAllFormatsObservable().test()

        test.assertComplete()
        test.assertValue { formats ->
            formats.size > 1 &&
                    formats.find { it == "Modern" } != null
        }
    }

    @Test
    fun getAllFormats() {
        val formatsResponse: Response<List<String>> = MtgFormatsApiClient.getAllFormats()
        val formats = formatsResponse.body()

        Assert.assertTrue(formatsResponse.isSuccessful)
        Assert.assertTrue(1 < formats!!.size)
    }
}