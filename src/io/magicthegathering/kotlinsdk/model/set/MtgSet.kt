package io.magicthegathering.kotlinsdk.model.set

import org.joda.time.DateTime

data class MtgSet(val code: String,
                  val name: String,
                  val type: String,
                  val border: String,
                  val mkm_id: Int,
                  val mkm_name: String,
                  @Transient var releaseDate: DateTime,
                  val magicCardsInfoCode: String,
                  val block: String)