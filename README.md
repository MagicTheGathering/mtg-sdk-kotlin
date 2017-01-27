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

### Cards

#### Get a Card
```kotlin
val multiverseId: Int = 409741

val observable: Observable<MtgCard> = MtgCardApiClient.getCard(multiverseId)
observable.subscribe { card: MtgCard ->
    ...
}
```

#### Get Cards by partial name
```kotlin
val partialName: String = "jace"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName(partialName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get Cards by exact name
```kotlin
val exactName: String = "zurgo helmsmasher"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByExactName(exactName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Generate a booster pack by set code
```kotlin
val setCode: String = "mm2"

val observable: Observable<List<MtgCard>> = MtgCardApiClient.generateBoosterPackBySetCode(setCode)
observable.subscribe { cards ->
    ...
}
```