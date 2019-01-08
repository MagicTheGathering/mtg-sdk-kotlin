package io.magicthegathering.kotlinsdk.model.set

import org.joda.time.DateTime

data class MtgSet(val code: String,
                  val name: String,
                  val type: String,
                  @Transient var releaseDate: DateTime,
                  val block: String)