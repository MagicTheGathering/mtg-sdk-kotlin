package io.magicthegathering.kotlinsdk.api.util

import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder

class DateTimeFormatterSingleton private constructor() {

    private object Holder {
        val INSTANCE: DateTimeFormatter = DateTimeFormatterBuilder()
                .appendYear(4, 4)
                .appendLiteral('-')
                .appendMonthOfYear(2)
                .appendLiteral('-')
                .appendDayOfMonth(2)
                .toFormatter()
    }

    companion object {
        val instance: DateTimeFormatter by lazy { Holder.INSTANCE }
    }
}