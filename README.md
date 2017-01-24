Magic: The Gathering Kotlin SDK 
===============================

[![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin.svg?branch=develop)](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin)


Kotlin SDK for using the [magicthegathering.io](http://magicthegathering.io) APIs.

Note that API use is free and does not require authentication or registration, but some rate limits apply. [Read the official API website for more information](https://docs.magicthegathering.io/#rate-limits).

Add the dependency to your project and you're good to go! 

Prerequisites
-------------

- Kotlin 1.0.6 or higher

Usage examples with Rx
----------------------

#### Get a Card
```kotlin
val multiverseId: Int = 409741

val observable: Observable<MtgCard> = MtgCardApiClient.instance.getCard(multiverseId)
observable.subscribe { card: MtgCard ->
    ...
}
```