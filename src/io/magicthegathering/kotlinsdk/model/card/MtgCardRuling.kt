package io.magicthegathering.kotlinsdk.model.card

import org.joda.time.DateTime

data class MtgCardRuling(val date: DateTime, val text: String)