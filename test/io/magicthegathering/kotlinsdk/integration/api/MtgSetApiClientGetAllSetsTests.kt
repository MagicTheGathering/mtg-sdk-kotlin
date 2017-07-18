package io.magicthegathering.kotlinsdk.integration.api

import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import org.junit.Test

class MtgSetApiClientGetAllSetsTests {

    @Test
    fun getAllSets() {
        val test = MtgSetApiClient.getAllSets().test()

        test.assertComplete()
        test.assertValue { sets ->
            sets.size > 1 &&
                    sets.find { it.code == "KTK" } != null
        }
    }
}