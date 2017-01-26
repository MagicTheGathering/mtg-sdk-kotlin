package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.card.MtgCardForeignName
import io.magicthegathering.kotlinsdk.model.card.MtgCardLegality
import io.magicthegathering.kotlinsdk.model.card.MtgCardRuling
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.onError

class MtgCardApiClientGetCardByMultiverseIdTests {

    @Test
    fun getSingleCardAndGetA404NotFoundError() {
        MtgCardApiClient.getCard(-1)
                .onErrorResumeNext { exception: Throwable ->
                    Observable.empty()
                }
                .onError { exception: Throwable ->
                    Assert.assertTrue(exception is HttpException)
                    val httpException: HttpException = exception as HttpException
                    Assert.assertEquals(404, httpException.code())
                    Assert.assertEquals("Not Found", httpException.message())
                }
                .subscribe { }
    }

    @Test
    fun getNormalCard() {
        MtgCardApiClient.getCard(1).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Ankh of Mishra")
            Assert.assertNull(card.names)
            Assert.assertEquals(card.manaCost, "{2}")
            Assert.assertEquals(card.cmc, 2)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Artifact")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Artifact")
            Assert.assertNull(card.subtypes)
            Assert.assertEquals(card.rarity, "Rare")
            Assert.assertEquals(card.set, "LEA")
            Assert.assertEquals(card.setName, "Limited Edition Alpha")
            Assert.assertEquals(card.text, "Whenever a land enters the battlefield, Ankh of Mishra deals 2 damage to that land's controller.")
            Assert.assertEquals(card.artist, "Amy Weber")
            Assert.assertNull(card.number)
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 1)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=1&type=card")
            Assert.assertEquals(card.layout, "normal")

            val legalities: List<MtgCardLegality> = arrayListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            val date: DateTime = DateTime.parse("2004-10-04", DateTimeFormatterSingleton.instance)

            val rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date, "This triggers on any land entering the battlefield. This includes playing a land or putting a land onto the battlefield using a spell or ability."),
                    MtgCardRuling(date, "It determines the land's controller at the time the ability resolves. If the land leaves the battlefield before the ability resolves, the land's last controller before it left is used.")
            )

            card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            Assert.assertNull(card.foreignNames)
        }
    }

    @Test
    fun getFlipCard() {
        MtgCardApiClient.getCard(78691).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Student of Elements")
            Assert.assertEquals(card.names!![0], "Student of Elements")
            Assert.assertEquals(card.names!![1], "Tobita, Master of Winds")
            Assert.assertEquals(card.manaCost, "{1}{U}")
            Assert.assertEquals(card.cmc, 2)
            Assert.assertEquals(card.colors!![0], "Blue")
            Assert.assertEquals(card.colorIdentity!![0], "U")
            Assert.assertEquals(card.type, "Creature — Human Wizard")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Creature")
            Assert.assertEquals(card.subtypes!![0], "Human")
            Assert.assertEquals(card.subtypes!![1], "Wizard")
            Assert.assertEquals(card.rarity, "Uncommon")
            Assert.assertEquals(card.set, "CHK")
            Assert.assertEquals(card.setName, "Champions of Kamigawa")
            Assert.assertEquals(card.text, "When Student of Elements has flying, flip it.")
            Assert.assertEquals(card.artist, "Ittoku")
            Assert.assertEquals(card.number, "93a")
            Assert.assertEquals(card.power, "1")
            Assert.assertEquals(card.toughness, "1")
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 78691)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=78691&type=card")
            Assert.assertEquals(card.layout, "flip")

            val legalities: List<MtgCardLegality> = arrayListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Kamigawa Block", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Modern", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            Assert.assertNull(card.rulings)

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=85443&type=card", "Chinese Simplified", 85443),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=85750&type=card", "French", 85750),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=86057&type=card", "German", 86057),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=86364&type=card", "Italian", 86364),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=86671&type=card", "Japanese", 86671),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=86978&type=card", "Portuguese (Brazil)", 86978),
                    MtgCardForeignName("Student of Elements", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=87285&type=card", "Spanish", 87285)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getDoubleFacedCard() {
        MtgCardApiClient.getCard(409741).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Archangel Avacyn")
            Assert.assertEquals(card.names!![0], "Archangel Avacyn")
            Assert.assertEquals(card.names!![1], "Avacyn, the Purifier")
            Assert.assertEquals(card.manaCost, "{3}{W}{W}")
            Assert.assertEquals(card.cmc, 5)
            Assert.assertEquals(card.colors!![0], "White")
            Assert.assertEquals(card.colorIdentity!![0], "W")
            Assert.assertEquals(card.type, "Legendary Creature — Angel")
            Assert.assertEquals(card.supertypes!![0], "Legendary")
            Assert.assertEquals(card.types[0], "Creature")
            Assert.assertEquals(card.subtypes!![0], "Angel")
            Assert.assertEquals(card.rarity, "Mythic Rare")
            Assert.assertEquals(card.set, "SOI")
            Assert.assertEquals(card.setName, "Shadows over Innistrad")
            Assert.assertEquals(card.text, "Flash\nFlying, vigilance\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.")
            Assert.assertEquals(card.artist, "James Ryman")
            Assert.assertEquals(card.number, "5a")
            Assert.assertEquals(card.power, "4")
            Assert.assertEquals(card.toughness, "4")
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 409741)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=409741&type=card")
            Assert.assertEquals(card.layout, "double-faced")

            val legalities: List<MtgCardLegality> = mutableListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Modern", "Legal"),
                    MtgCardLegality("Shadows over Innistrad Block", "Legal"),
                    MtgCardLegality("Standard", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            val date1: DateTime = DateTime.parse("2016-04-08", DateTimeFormatterSingleton.instance)
            val date2: DateTime = DateTime.parse("2016-07-13", DateTimeFormatterSingleton.instance)

            val rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date1, "Archangel Avacyn's delayed triggered ability triggers at the beginning of the next upkeep regardless of whose turn it is."),
                    MtgCardRuling(date1, "Archangel Avacyn's delayed triggered ability won't cause it to transform back into Archangel Avacyn if it has already transformed into Avacyn, the Purifier, perhaps because several creatures died in one turn."),
                    MtgCardRuling(date2, "For more information on double-faced cards, see the Shadows over Innistrad mechanics article (http://magic.wizards.com/en/articles/archive/feature/shadows-over-innistrad-mechanics).")
            )

            card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("大天使艾维欣", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=410071&type=card", "Chinese Simplified", 410071),
                    MtgCardForeignName("大天使艾維欣", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=410401&type=card", "Chinese Traditional", 410401),
                    MtgCardForeignName("Archange Avacyn", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=411061&type=card", "French", 411061),
                    MtgCardForeignName("Erzengel Avacyn", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=410731&type=card", "German", 410731),
                    MtgCardForeignName("Arcangelo Avacyn", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=411391&type=card", "Italian", 411391),
                    MtgCardForeignName("大天使アヴァシン", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=411721&type=card", "Japanese", 411721),
                    MtgCardForeignName("대천사 아바신", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=412051&type=card", "Korean", 412051),
                    MtgCardForeignName("Arcanjo Avacyn", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=412381&type=card", "Portuguese (Brazil)", 412381),
                    MtgCardForeignName("Архангел Авацина", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=412711&type=card", "Russian", 412711),
                    MtgCardForeignName("Arcángel Avacyn", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=413041&type=card", "Spanish", 413041)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getSplitCard() {
        MtgCardApiClient.getCard(20578).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Pain")
            Assert.assertEquals(card.names!![0], "Pain")
            Assert.assertEquals(card.names!![1], "Suffering")
            Assert.assertEquals(card.manaCost, "{B}")
            Assert.assertEquals(card.cmc, 1)
            Assert.assertEquals(card.colors!![0], "Black")
            Assert.assertEquals(card.colorIdentity!![0], "R")
            Assert.assertEquals(card.colorIdentity!![1], "B")
            Assert.assertEquals(card.type, "Sorcery")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Sorcery")
            Assert.assertNull(card.subtypes)
            Assert.assertEquals(card.rarity, "Uncommon")
            Assert.assertEquals(card.set, "INV")
            Assert.assertEquals(card.setName, "Invasion")
            Assert.assertEquals(card.text, "Target player discards a card.")
            Assert.assertEquals(card.artist, "David Martin")
            Assert.assertEquals(card.number, "294a")
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 20578)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=20578&type=card")
            Assert.assertEquals(card.layout, "split")

            val legalities: List<MtgCardLegality> = arrayListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Invasion Block", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            Assert.assertNull(card.rulings)

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("Pain", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=185588&type=card", "French", 185588),
                    MtgCardForeignName("Pain", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=185589&type=card", "French", 185589),
                    MtgCardForeignName("Pain", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=183714&type=card", "German", 183714),
                    MtgCardForeignName("Pain", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=183715&type=card", "German", 183715)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getTokenCard() {
        MtgCardApiClient.getCard(159048).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Elemental")
            Assert.assertNull(card.names)
            Assert.assertNull(card.manaCost)
            Assert.assertNull(card.cmc)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Creature — Elemental")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Creature")
            Assert.assertEquals(card.subtypes!![0], "Elemental")
            Assert.assertEquals(card.rarity, "Common")
            Assert.assertEquals(card.set, "EVG")
            Assert.assertEquals(card.setName, "Duel Decks: Elves vs. Goblins")
            Assert.assertEquals(card.text, "Trample")
            Assert.assertEquals(card.artist, "Anthony S. Waters")
            Assert.assertEquals(card.number, "T1")
            Assert.assertEquals(card.power, "7")
            Assert.assertEquals(card.toughness, "7")
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 159048)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=159048&type=card")
            Assert.assertEquals(card.layout, "token")
            Assert.assertNull(card.legalities)
            Assert.assertNull(card.rulings)
            Assert.assertNull(card.foreignNames)
        }
    }

    @Test
    fun getPlaneCard() {
        MtgCardApiClient.getCard(198073).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Academy at Tolaria West")
            Assert.assertNull(card.names)
            Assert.assertNull(card.manaCost)
            Assert.assertNull(card.cmc)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Plane — Dominaria")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Plane")
            Assert.assertEquals(card.subtypes!![0], "Dominaria")
            Assert.assertEquals(card.rarity, "Common")
            Assert.assertEquals(card.set, "HOP")
            Assert.assertEquals(card.setName, "Planechase")
            Assert.assertEquals(card.text, "At the beginning of your end step, if you have no cards in hand, draw seven cards.\nWhenever you roll CHAOS, discard your hand.")
            Assert.assertEquals(card.artist, "James Paick")
            Assert.assertEquals(card.number, "1")
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 198073)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=198073&type=card")
            Assert.assertEquals(card.layout, "plane")
            Assert.assertNull(card.legalities)

            val date: DateTime = DateTime.parse("2009-10-01", DateTimeFormatterSingleton.instance)

            val rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date, "A plane card is treated as if its text box included \"When you roll {PW}, put this card on the bottom of its owner's planar deck face down, then move the top card of your planar deck off that planar deck and turn it face up.\" This is called the \"planeswalking ability.\""),
                    MtgCardRuling(date, "A face-up plane card that's turned face down becomes a new object with no relation to its previous existence. In particular, it loses all counters it may have had."),
                    MtgCardRuling(date, "The controller of a face-up plane card is the player designated as the \"planar controller.\" Normally, the planar controller is whoever the active player is. However, if the current planar controller would leave the game, instead the next player in turn order that wouldn't leave the game becomes the planar controller, then the old planar controller leaves the game. The new planar controller retains that designation until he or she leaves the game or a different player becomes the active player, whichever comes first."),
                    MtgCardRuling(date, "If an ability of a plane refers to \"you,\" it's referring to whoever the plane's controller is at the time, not to the player that started the game with that plane card in his or her deck. Many abilities of plane cards affect all players, while many others affect only the planar controller, so read each ability carefully."),
                    MtgCardRuling(date, "Academy at Tolaria West's first ability has an \"intervening 'if' clause.\" That means (1) the ability won't trigger at all unless you have no cards in hand as your end step begins, and (2) the ability will do nothing unless you have no cards in hand by the time it resolves."),
                    MtgCardRuling(date, "If you discard your hand as a result of rolling {CHAOS}, Academy at Tolaria West's first ability will then trigger at the beginning of your end step (unless you planeswalk or somehow put a card in your hand before then).")
            )

            card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("Académie de l'Ouest de Tolaria", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=211853&type=card", "French", 211853),
                    MtgCardForeignName("Akademie von West-Tolaria", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=211763&type=card", "German", 211763),
                    MtgCardForeignName("Accademia di Tolaria Occidentale", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=211808&type=card", "Italian", 211808),
                    MtgCardForeignName("トレイリア西部のアカデミー", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=211943&type=card", "Japanese", 211943),
                    MtgCardForeignName("Academia en Tolaria Oeste", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=211898&type=card", "Spanish", 211898)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getSchemeCard() {
        MtgCardApiClient.getCard(212648).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "All in Good Time")
            Assert.assertNull(card.names)
            Assert.assertNull(card.manaCost)
            Assert.assertNull(card.cmc)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Scheme")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Scheme")
            Assert.assertNull(card.subtypes)
            Assert.assertEquals(card.rarity, "Common")
            Assert.assertEquals(card.set, "ARC")
            Assert.assertEquals(card.setName, "Archenemy")
            Assert.assertEquals(card.text, "When you set this scheme in motion, take an extra turn after this one. Schemes can't be set in motion that turn.")
            Assert.assertEquals(card.artist, "Nic Klein")
            Assert.assertEquals(card.number, "1")
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 212648)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=212648&type=card")
            Assert.assertEquals(card.layout, "scheme")
            Assert.assertNull(card.legalities)
            Assert.assertNull(card.rulings)

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("Toute chose en son temps", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=235062&type=card", "French", 235062),
                    MtgCardForeignName("Alles zu seiner Zeit", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=234912&type=card", "German", 234912),
                    MtgCardForeignName("Tutto a Suo Tempo", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=234862&type=card", "Italian", 234862),
                    MtgCardForeignName("全ては成されるべきもの", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=234962&type=card", "Japanese", 234962),
                    MtgCardForeignName("Cada cosa a su tiempo", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=235012&type=card", "Spanish", 235012)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getPhenomenonCard() {
        MtgCardApiClient.getCard(226509).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Chaotic Aether")
            Assert.assertNull(card.names)
            Assert.assertNull(card.manaCost)
            Assert.assertNull(card.cmc)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Phenomenon")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Phenomenon")
            Assert.assertNull(card.subtypes)
            Assert.assertEquals(card.rarity, "Common")
            Assert.assertEquals(card.set, "PC2")
            Assert.assertEquals(card.setName, "Planechase 2012 Edition")
            Assert.assertEquals(card.text, "When you encounter Chaotic Aether, each blank roll of the planar die is a CHAOS roll until a player planeswalks away from a plane. (Then planeswalk away from this phenomenon.)")
            Assert.assertEquals(card.artist, "Dan Scott")
            Assert.assertEquals(card.number, "1")
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 226509)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=226509&type=card")
            Assert.assertEquals(card.layout, "phenomenon")
            Assert.assertNull(card.legalities)

            val date: DateTime = DateTime.parse("2012-06-01", DateTimeFormatterSingleton.instance)

            val rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date, "While Chaotic Aether's effect applies, rolling any blank face of the planar die will cause chaos abilities to trigger."),
                    MtgCardRuling(date, "Planeswalking away from a phenomenon (as you do when resolving Chaotic Aether's ability) doesn't cause Chaotic Aether's effect to expire.")
            )

            card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("Æther chaotique", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=288474&type=card", "French", 288474),
                    MtgCardForeignName("Chaotischer Äther", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=288515&type=card", "German", 288515),
                    MtgCardForeignName("Etere Caotico", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=288597&type=card", "Italian", 288597),
                    MtgCardForeignName("混沌の霊気", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=288638&type=card", "Japanese", 288638),
                    MtgCardForeignName("Éter caótico", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=288556&type=card", "Spanish", 288556)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getLevelerCard() {
        MtgCardApiClient.getCard(194918).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Caravan Escort")
            Assert.assertNull(card.names)
            Assert.assertEquals(card.manaCost, "{W}")
            Assert.assertEquals(card.cmc, 1)
            Assert.assertEquals(card.colors!![0], "White")
            Assert.assertEquals(card.colorIdentity!![0], "W")
            Assert.assertEquals(card.type, "Creature — Human Knight")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Creature")
            Assert.assertEquals(card.subtypes!![0], "Human")
            Assert.assertEquals(card.subtypes!![1], "Knight")
            Assert.assertEquals(card.rarity, "Common")
            Assert.assertEquals(card.set, "ROE")
            Assert.assertEquals(card.setName, "Rise of the Eldrazi")
            Assert.assertEquals(card.text, "Level up {2} ({2}: Put a level counter on this. Level up only as a sorcery.)\nLEVEL 1-4\n2/2\nLEVEL 5+\n5/5\nFirst strike")
            Assert.assertEquals(card.artist, "Goran Josic")
            Assert.assertEquals(card.number, "15")
            Assert.assertEquals(card.power, "1")
            Assert.assertEquals(card.toughness, "1")
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 194918)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=194918&type=card")
            Assert.assertEquals(card.layout, "leveler")

            val legalities: List<MtgCardLegality> = arrayListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Modern", "Legal"),
                    MtgCardLegality("Vintage", "Legal"),
                    MtgCardLegality("Zendikar Block", "Legal")
            )

            card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            val date: DateTime = DateTime.parse("2010-06-15", DateTimeFormatterSingleton.instance)

            val rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date, "The abilities a leveler grants to itself don't overwrite any other abilities it may have. In particular, they don't overwrite the creature's level up ability; it always has that."),
                    MtgCardRuling(date, "Effects that set a leveler's power or toughness to a specific value, including the effects from a level symbol's ability, apply in timestamp order. The timestamp of each level symbol's ability is the same as the timestamp of the leveler itself, regardless of when the most recent level counter was put on it."),
                    MtgCardRuling(date, "Effects that modify a leveler's power or toughness, such as the effects of Giant Growth or Glorious Anthem, will apply to it no matter when they started to take effect. The same is true for counters that change the creature's power or toughness (such as +1/+1 counters) and effects that switch its power and toughness."),
                    MtgCardRuling(date, "If another creature becomes a copy of a leveler, all of the leveler's printed abilities -- including those represented by level symbols -- are copied. The current characteristics of the leveler, and the number of level counters on it, are not. The abilities, power, and toughness of the copy will be determined based on how many level counters are on the copy."),
                    MtgCardRuling(date, "A creature's level is based on how many level counters it has on it, not how many times its level up ability has been activated or has resolved. If a leveler gets level counters due to some other effect (such as Clockspinning) or loses level counters for some reason (such as Vampire Hexmage), its level is changed accordingly.")
            )

            card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            val foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("商队卫士", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=216227&type=card", "Chinese Simplified", 216227),
                    MtgCardForeignName("Escorte de caravane", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=215731&type=card", "French", 215731),
                    MtgCardForeignName("Karawaneneskorte", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=217467&type=card", "German", 217467),
                    MtgCardForeignName("Scorta della Carovana", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=216475&type=card", "Italian", 216475),
                    MtgCardForeignName("隊商の随員", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=216723&type=card", "Japanese", 216723),
                    MtgCardForeignName("Escolta de Caravana", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=216971&type=card", "Portuguese (Brazil)", 216971),
                    MtgCardForeignName("Сопроводитель Каравана", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=215979&type=card", "Russian", 215979),
                    MtgCardForeignName("Escolta de caravana", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=217219&type=card", "Spanish", 217219)
            )

            card.foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getVanguardCard() {
        MtgCardApiClient.getCard(12329).subscribe { card: MtgCard ->
            Assert.assertEquals(card.name, "Ashnod")
            Assert.assertNull(card.names)
            Assert.assertNull(card.manaCost)
            Assert.assertNull(card.cmc)
            Assert.assertNull(card.colors)
            Assert.assertNull(card.colorIdentity)
            Assert.assertEquals(card.type, "Vanguard")
            Assert.assertNull(card.supertypes)
            Assert.assertEquals(card.types[0], "Vanguard")
            Assert.assertNull(card.subtypes)
            Assert.assertEquals(card.rarity, "Special")
            Assert.assertEquals(card.set, "VAN")
            Assert.assertEquals(card.setName, "Vanguard")
            Assert.assertEquals(card.text, "Whenever a creature deals damage to you, destroy it.")
            Assert.assertEquals(card.artist, "Ron Spencer")
            Assert.assertNull(card.number)
            Assert.assertNull(card.power)
            Assert.assertNull(card.toughness)
            Assert.assertNull(card.loyalty)
            Assert.assertEquals(card.multiverseid, 12329)
            Assert.assertEquals(card.imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=12329&type=card")
            Assert.assertEquals(card.layout, "vanguard")
        }
    }
}