package io.magicthegathering.kotlinsdk.model.card

data class MtgCardForeignName(
        val name: String,
        val imageUrl: String?,
        val language: String,
        val multiverseid: Int?
)