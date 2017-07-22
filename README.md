Magic: The Gathering Kotlin SDK 
===============================

[![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin.svg?branch=develop)](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin)


Kotlin SDK for using the [magicthegathering.io](http://magicthegathering.io) APIs.

Note that API use is free and does not require authentication or registration, but some rate limits apply. [Read the official API website for more information](https://docs.magicthegathering.io/#rate-limits).

Add the dependency to your project and you're good to go! 

Prerequisites
-------------

- Kotlin 1.0.6 or higher

Usage examples
--------------

### Cards

#### Get all cards with default values (pageSize = 10, page = 0)
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCards()
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get all cards with pageSize
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCards(50)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

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

#### Get Cards by partial name with non English language
```kotlin
val language: String = "spanish"
val partialName: String = "jace"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage(language, partialName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get Cards by exact name with non English language
```kotlin
val language: String = "spanish"
val exactName: String = "zurgo aplastacráneos"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage(language, exactName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

### Sets

#### Get all sets as abservable
```kotlin
val observable: Observable<List<MtgSet>> = MtgSetApiClient.getAllSetsObservable()
observable.subscribe { sets ->
    ...
}
```

#### Get all sets
```kotlin
val setsResponse: Response<List<MtgSet>> = MtgSetApiClient.getAllSets()
val sets = setsResponse.body()
```

#### Get a specific set as observable
```kotlin
val setCode: String = "mm2"

val observable: Observable<MtgSet> = MtgSetApiClient.getSpecificSetObservable(setCode)
observable.subscribe { set ->
    ...
}
```

#### Get a specific set
```kotlin
val setCode: String = "mm2"

val setResponse: Response<MtgSet> = MtgSetApiClient.getSpecificSet(setCode)
val set = setResponse.body()
```

#### Generate a booster pack by set code as observable
```kotlin
val setCode: String = "mm2"

val observable: Observable<List<MtgCard>> = MtgSetApiClient.generateBoosterPackBySetCodeObservable(setCode)
observable.subscribe { cards ->
    ...
}
```

#### Generate a booster pack by set code
```kotlin
val setCode: String = "mm2"

val cardsResponse: Response<List<MtgCard>> = MtgSetApiClient.generateBoosterPackBySetCode(setCode)
val cards = cardsResponse.body()
```

### Card Types

#### Get all card types as observable
```kotlin
val observable: Observable<List<String>> = MtgCardTypesApiClient.getAllTypesObservable()
observable.subscribe { cardTypes ->
    ...
}
```

#### Get all card types
```kotlin
val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllTypes()
val cardTypes = cardTypesResponse.body()
```

#### Get all card supertypes as observable
```kotlin
val observable: Observable<List<String>> = MtgCardTypesApiClient.getAllSupertypesObservable()
observable.subscribe { cardTypes ->
    ...
}
```

#### Get all card supertypes
```kotlin
val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllSupertypes()
val cardTypes = cardTypesResponse.body()
```

#### Get all card subtypes as observable
```kotlin
val observable: Observable<List<String>> = MtgCardTypesApiClient.getAllSubtypesObservable()
observable.subscribe { cardTypes ->
    ...
}
```

#### Get all card subtypes
```kotlin
val cardTypesResponse: Response<List<String>> = MtgCardTypesApiClient.getAllSubtypes()
val cardTypes = cardTypesResponse.body()
```

### Formats

#### Get all formats as observable
```kotlin
val observable: Observable<List<String>> = MtgFormatsApiClient.getAllFormatsObservable()
observable.subscribe { formats ->
    ...
}
```

#### Get all formats
```kotlin
val formatsResponse: Response<List<String>> = MtgFormatsApiClient.getAllFormats()
val formats = formatsResponse.body()
```