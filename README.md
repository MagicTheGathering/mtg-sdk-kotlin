Magic: The Gathering Kotlin SDK 
===============================

Kotlin SDK for using the [magicthegathering.io](http://magicthegathering.io) APIs.

Note that API use is free and does not require authentication or registration, but some rate limits apply. [Read the official API website for more information](https://docs.magicthegathering.io/#documentationrate_limits).

| Bintray JCenter | Build Status |
|:---------------:|:------------:|
| [ ![jcenter](https://api.bintray.com/packages/asantalla/develoop/magicthegatheringio-kotlin-sdk/images/download.svg) ](https://bintray.com/asantalla/develoop/magicthegatheringio-kotlin-sdk/_latestVersion) | [![Build Status](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin.svg?branch=develop)](https://travis-ci.org/MagicTheGathering/mtg-sdk-kotlin) |

Add the dependency to your project and you're good to go!
 
Download
--------

First of all, be sure you have the **jcenter repository** included in the `build.gradle` file in the root of your project.

```
repositories {
    jcenter()
}
```

Next add the gradle compile dependency to the `build.gradle` file of your app module.

```
compile 'io.magicthegathering:kotlinsdk:1.0.3'
```

Prerequisites
-------------

- Kotlin 1.0.6 or higher

Usage examples
--------------

### Cards

#### Get all cards with default values (pageSize = 10, page = 0) as observable
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCardsObservable()
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get all cards with default values (pageSize = 10, page = 0)
```kotlin
val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCards()
val cards = cardsResponse.body()
```

#### Get all cards by set code with default values (pageSize = 10, page = 0) as observable
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCodeObservable("DOM")
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get all cards by set code with default values (pageSize = 10, page = 0)
```kotlin
val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCode("DOM")
val cards = cardsResponse.body()
```

#### Get all cards with pageSize as observable
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCardsObservable(50)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get all cards with pageSize
```kotlin
val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCards(50)
val cards = cardsResponse.body()
```

#### Get all cards by set code with pageSize as observable
```kotlin
val observable: Observable<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCodeObservable("DOM", 50)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get all cards by set code with pageSize
```kotlin
val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getAllCardsBySetCode("DOM", 50)
val cards = cardsResponse.body()
```

#### Get a Card as observable
```kotlin
val multiverseId: Int = 409741

val observable: Observable<MtgCard> = MtgCardApiClient.getCardObservable(multiverseId)
observable.subscribe { card: MtgCard ->
    ...
}
```

#### Get a Card
```kotlin
val multiverseId: Int = 409741

val cardResponse: Response<MtgCard> = MtgCardApiClient.getCard(multiverseId)
val card = cardResponse.body()
```

#### Get Cards by partial name as observable
```kotlin
val partialName: String = "jace"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameObservable(partialName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get Cards by partial name
```kotlin
val partialName: String = "jace"
val pageSize: Int = 2
val page: Int = 1

val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName(partialName, pageSize, page)
val cards = cardsResponse.body()
```

#### Get Cards by exact name as observable
```kotlin
val exactName: String = "zurgo helmsmasher"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameObservable(exactName, pageSize, page)
observable.subscribe { cards: List<MtgCard> ->
    ...
}
```

#### Get Cards by exact name
```kotlin
val exactName: String = "zurgo helmsmasher"
val pageSize: Int = 2
val page: Int = 1

val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactName(exactName, pageSize, page)
val cards = cardsResponse.body()
```

#### Get Cards by partial name with non English language as observable
```kotlin
val language: String = "spanish"
val partialName: String = "jace"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguageObservable(language, partialName, pageSize, page)
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

val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialNameWithNonEnglishLanguage(language, partialName, pageSize, page)
val cards = cardsResponse.body()
```

#### Get Cards by exact name with non English language as observable
```kotlin
val language: String = "spanish"
val exactName: String = "zurgo aplastacráneos"
val pageSize: Int = 2
val page: Int = 1

val observable: Observable<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguageObservable(language, exactName, pageSize, page)
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

val cardsResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByExactNameWithNonEnglishLanguage(languageø, exactName, pageSize, page)
val cards = cardsResponse.body()
```

### Sets

#### Get all sets as observable
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