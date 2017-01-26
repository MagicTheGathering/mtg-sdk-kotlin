package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.card.MtgCardForeignName
import io.magicthegathering.kotlinsdk.model.card.MtgCardLegality
import io.magicthegathering.kotlinsdk.model.card.MtgCardRuling
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class MtgCardApiClientGetCardsByNameTests {

    @Test
    fun getCardsByPartialNameNoResult() {
        MtgCardApiClient.getCardsByPartialName("no_card", 10, 1).subscribe { cards ->
            Assert.assertTrue(cards.isEmpty())
        }
    }

    @Test
    fun getCardsByPartialName() {
        MtgCardApiClient.getCardsByPartialName("jace", 2, 1).subscribe { cards ->
            Assert.assertEquals(cards[0].name, "Jace Beleren")
            Assert.assertNull(cards[0].names)
            Assert.assertEquals(cards[0].manaCost, "{1}{U}{U}")
            Assert.assertEquals(cards[0].cmc, 3)
            Assert.assertEquals(cards[0].colors!![0], "Blue")
            Assert.assertEquals(cards[0].colorIdentity!![0], "U")
            Assert.assertEquals(cards[0].type, "Planeswalker — Jace")
            Assert.assertNull(cards[0].supertypes)
            Assert.assertEquals(cards[0].types[0], "Planeswalker")
            Assert.assertEquals(cards[0].subtypes!![0], "Jace")
            Assert.assertEquals(cards[0].rarity, "Special")
            Assert.assertEquals(cards[0].set, "pMEI")
            Assert.assertEquals(cards[0].setName, "Media Inserts")
            Assert.assertEquals(cards[0].text, "+2: Each player draws a card.\n−1: Target player draws a card.\n−10: Target player puts the top twenty cards of his or her library into his or her graveyard.")
            Assert.assertEquals(cards[0].artist, "Aleksi Briclot")
            Assert.assertEquals(cards[0].number, "15")
            Assert.assertNull(cards[0].power)
            Assert.assertNull(cards[0].toughness)
            Assert.assertEquals(cards[0].loyalty, 3)
            Assert.assertNull(cards[0].multiverseid)
            Assert.assertNull(cards[0].imageUrl)
            Assert.assertEquals(cards[0].layout, "normal")

            var legalities: List<MtgCardLegality> = mutableListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Lorwyn-Shadowmoor Block", "Legal"),
                    MtgCardLegality("Modern", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            cards[0].legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            var date: DateTime = DateTime.parse("2009-10-01", DateTimeFormatterSingleton.instance)

            var rulings: List<MtgCardRuling> = arrayListOf(
                    MtgCardRuling(date, "If there are fewer than twenty cards in the targeted player's library, that player puts all the cards from his or her library into his or her graveyard.")
            )

            cards[0].rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            var foreignNames: List<MtgCardForeignName> = arrayListOf(
                    MtgCardForeignName("杰斯贝连", null, "Chinese Simplified", null),
                    MtgCardForeignName("傑斯貝連", null, "Chinese Traditional", null),
                    MtgCardForeignName("Jace Beleren", null, "French", null),
                    MtgCardForeignName("Jace Beleren", null, "German", null),
                    MtgCardForeignName("Jace Beleren", null, "Italian", null),
                    MtgCardForeignName("ジェイス・ベレレン", null, "Japanese", null),
                    MtgCardForeignName("Jace Beleren", null, "Portuguese (Brazil)", null),
                    MtgCardForeignName("Джейс Белерен", null, "Russian", null),
                    MtgCardForeignName("Jace Beleren", null, "Spanish", null)

            )

            cards[0].foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }

            Assert.assertEquals(cards[1].name, "Jace, Memory Adept")
            Assert.assertNull(cards[1].names)
            Assert.assertEquals(cards[1].manaCost, "{3}{U}{U}")
            Assert.assertEquals(cards[1].cmc, 5)
            Assert.assertEquals(cards[1].colors!![0], "Blue")
            Assert.assertNull(cards[1].colorIdentity)
            Assert.assertEquals(cards[1].type, "Planeswalker — Jace")
            Assert.assertNull(cards[1].supertypes)
            Assert.assertEquals(cards[1].types[0], "Planeswalker")
            Assert.assertEquals(cards[1].subtypes!![0], "Jace")
            Assert.assertEquals(cards[1].rarity, "Special")
            Assert.assertEquals(cards[1].set, "pMEI")
            Assert.assertEquals(cards[1].setName, "Media Inserts")
            Assert.assertEquals(cards[1].text, "+1: Draw a card. Target player puts the top card of his or her library into his or her graveyard.\n0: Target player puts the top ten cards of his or her library into his or her graveyard.\n−7: Any number of target players each draw twenty cards.")
            Assert.assertEquals(cards[1].artist, "Steve Prescott")
            Assert.assertEquals(cards[1].number, "73")
            Assert.assertNull(cards[1].power)
            Assert.assertNull(cards[1].toughness)
            Assert.assertEquals(cards[1].loyalty, 4)
            Assert.assertNull(cards[1].multiverseid)
            Assert.assertNull(cards[1].imageUrl)
            Assert.assertEquals(cards[1].layout, "normal")

            legalities = mutableListOf(
                    MtgCardLegality("Commander", "Legal"),
                    MtgCardLegality("Legacy", "Legal"),
                    MtgCardLegality("Modern", "Legal"),
                    MtgCardLegality("Vintage", "Legal")
            )

            cards[1].legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
            }

            date = DateTime.parse("2011-09-22", DateTimeFormatterSingleton.instance)

            rulings = arrayListOf(
                    MtgCardRuling(date, "If you target yourself with Jace's first ability, you'll draw a card first, then put the top card of your library into your graveyard."),
                    MtgCardRuling(date, "If you activate Jace's first ability, and the player is an illegal target when the ability tries to resolve, it will be countered and none of its effects will happen. You won't draw a card."),
                    MtgCardRuling(date, "If Jace's third ability causes a player to draw more cards than are left in his or her library, that player loses the game as a state-based action. If this ability causes all players to do this, the game is a draw.")
            )

            cards[1].rulings!!.forEachIndexed { index, mtgCardRuling ->
                Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
            }

            foreignNames = arrayListOf(
                    MtgCardForeignName("记忆专家杰斯", null, "Chinese Simplified", null),
                    MtgCardForeignName("記憶專家傑斯", null, "Chinese Traditional", null),
                    MtgCardForeignName("Jace, expert en mémoire", null, "French", null),
                    MtgCardForeignName("Jace, Meister der Erinnerung", null, "German", null),
                    MtgCardForeignName("Jace, Esperto di Ricordi", null, "Italian", null),
                    MtgCardForeignName("記憶の熟達者、ジェイス", null, "Japanese", null),
                    MtgCardForeignName("기억조작술사 제이스", null, "Korean", null),
                    MtgCardForeignName("Jace, Adepto da Memória", null, "Portuguese (Brazil)", null),
                    MtgCardForeignName("Джейс, Адепт Памяти", null, "Russian", null),
                    MtgCardForeignName("Jace, perito de la memoria", null, "Spanish", null)
            )

            cards[1].foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }

    @Test
    fun getCardsByExactNameNoResult() {
        MtgCardApiClient.getCardsByExactName("no_card", 10, 1).subscribe { cards ->
            Assert.assertTrue(cards.isEmpty())
        }
    }

    @Test
    fun getCardsByExactName() {
        MtgCardApiClient.getCardsByExactName("zurgo helmsmasher", 3, 1).subscribe { cards: List<MtgCard> ->
            cards.forEach { card ->
                Assert.assertEquals(card.name, "Zurgo Helmsmasher")
                Assert.assertNull(card.names)
                Assert.assertEquals(card.manaCost, "{2}{R}{W}{B}")
                Assert.assertEquals(card.cmc, 5)
                Assert.assertEquals(card.colors!![0], "White")
                Assert.assertEquals(card.colors!![1], "Black")
                Assert.assertEquals(card.colors!![2], "Red")
                Assert.assertEquals(card.colorIdentity!![0], "W")
                Assert.assertEquals(card.colorIdentity!![1], "B")
                Assert.assertEquals(card.colorIdentity!![2], "R")
                Assert.assertEquals(card.type, "Legendary Creature — Orc Warrior")
                Assert.assertEquals(card.supertypes!![0], "Legendary")
                Assert.assertEquals(card.types[0], "Creature")
                Assert.assertEquals(card.subtypes!![0], "Orc")
                Assert.assertEquals(card.subtypes!![1], "Warrior")
                Assert.assertEquals(card.text, "Haste\nZurgo Helmsmasher attacks each combat if able.\nZurgo Helmsmasher has indestructible as long as it's your turn.\nWhenever a creature dealt damage by Zurgo Helmsmasher this turn dies, put a +1/+1 counter on Zurgo Helmsmasher.")
                Assert.assertEquals(card.power, "7")
                Assert.assertEquals(card.toughness, "2")
                Assert.assertNull(card.loyalty)
                Assert.assertEquals(card.layout, "normal")

                val legalities = mutableListOf(
                        MtgCardLegality("Commander", "Legal"),
                        MtgCardLegality("Khans of Tarkir Block", "Legal"),
                        MtgCardLegality("Legacy", "Legal"),
                        MtgCardLegality("Modern", "Legal"),
                        MtgCardLegality("Vintage", "Legal")
                )

                card.legalities!!.forEachIndexed { index, mtgCardLegalitylegality ->
                    Assert.assertEquals(mtgCardLegalitylegality.format, legalities[index].format)
                    Assert.assertEquals(mtgCardLegalitylegality.legality, legalities[index].legality)
                }

                val date = DateTime.parse("2014-09-20", DateTimeFormatterSingleton.instance)

                val rulings = arrayListOf(
                        MtgCardRuling(date, "You still choose which player or planeswalker Zurgo Helmsmasher attacks."),
                        MtgCardRuling(date, "If, during your declare attackers step, Zurgo is tapped or is affected by a spell or ability that says it can't attack, then it doesn't attack. If there's a cost associated with having Zurgo attack, you aren't forced to pay that cost, so it doesn't have to attack in that case either."),
                        MtgCardRuling(date, "If Zurgo enters the battlefield before the combat phase, it will attack that turn if able. If it enters the battlefield after combat, it won't attack that turn and will usually be available to block on the following turn."),
                        MtgCardRuling(date, "Each time a creature dies, check whether Zurgo had dealt any damage to it at any time during that turn. If so, Zurgo's ability will trigger. It doesn't matter who controlled the creature or whose graveyard it went to.")
                )

                card.rulings!!.forEachIndexed { index, mtgCardRuling ->
                    Assert.assertEquals(mtgCardRuling.date, rulings[index].date)
                    Assert.assertEquals(mtgCardRuling.text, rulings[index].text)
                }
            }

            Assert.assertEquals(cards[0].rarity, "Special")
            Assert.assertEquals(cards[0].set, "pPRE")
            Assert.assertEquals(cards[0].setName, "Prerelease Events")
            Assert.assertEquals(cards[0].artist, "Aleksi Briclot")
            Assert.assertEquals(cards[0].number, "127")
            Assert.assertNull(cards[0].multiverseid)
            Assert.assertNull(cards[0].imageUrl)

            var foreignNames = arrayListOf(
                    MtgCardForeignName("碎盔珠高", null, "Chinese Simplified", null),
                    MtgCardForeignName("碎盔珠高", null, "Chinese Traditional", null),
                    MtgCardForeignName("Zurgo Brisecasque", null, "French", null),
                    MtgCardForeignName("Zurgo Helmbrecher", null, "German", null),
                    MtgCardForeignName("Zurgo Spaccaelmi", null, "Italian", null),
                    MtgCardForeignName("兜砕きのズルゴ", null, "Japanese", null),
                    MtgCardForeignName("투구분쇄자 주르고", null, "Korean", null),
                    MtgCardForeignName("Zurgo Quebra-elmo", null, "Portuguese (Brazil)", null),
                    MtgCardForeignName("Зурго Шлемобой", null, "Russian", null),
                    MtgCardForeignName("Zurgo Aplastacráneos", null, "Spanish", null)
            )

            cards[0].foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }

            Assert.assertEquals(cards[1].rarity, "Mythic Rare")
            Assert.assertEquals(cards[1].set, "DDN")
            Assert.assertEquals(cards[1].setName, "Duel Decks: Speed vs. Cunning")
            Assert.assertEquals(cards[1].artist, "Ryan Alexander Lee")
            Assert.assertEquals(cards[1].number, "1")
            Assert.assertEquals(cards[1].multiverseid, 386380)
            Assert.assertEquals(cards[1].imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386380&type=card")

            foreignNames = arrayListOf(
                    MtgCardForeignName("兜砕きのズルゴ", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386462&type=card", "Japanese", 386462)
            )

            cards[1].foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }

            Assert.assertEquals(cards[2].rarity, "Mythic Rare")
            Assert.assertEquals(cards[2].set, "KTK")
            Assert.assertEquals(cards[2].setName, "Khans of Tarkir")
            Assert.assertEquals(cards[2].artist, "Aleksi Briclot")
            Assert.assertEquals(cards[2].number, "214")
            Assert.assertEquals(cards[2].multiverseid, 386731)
            Assert.assertEquals(cards[2].imageUrl, "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386731&type=card")

            foreignNames = arrayListOf(
                    MtgCardForeignName("碎盔珠高", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=387000&type=card", "Chinese Simplified", 387000),
                    MtgCardForeignName("碎盔珠高", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=387269&type=card", "Chinese Traditional", 387269),
                    MtgCardForeignName("Zurgo Brisecasque", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=387807&type=card", "French", 387807),
                    MtgCardForeignName("Zurgo Helmbrecher", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=387538&type=card", "German", 387538),
                    MtgCardForeignName("Zurgo Spaccaelmi", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=388076&type=card", "Italian", 388076),
                    MtgCardForeignName("兜砕きのズルゴ", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=388345&type=card", "Japanese", 388345),
                    MtgCardForeignName("투구분쇄자 주르고", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=388614&type=card", "Korean", 388614),
                    MtgCardForeignName("Zurgo Quebra-elmo", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=388883&type=card", "Portuguese (Brazil)", 388883),
                    MtgCardForeignName("Зурго Шлемобой", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=389152&type=card", "Russian", 389152),
                    MtgCardForeignName("Zurgo Aplastacráneos", "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=389421&type=card", "Spanish", 389421)
            )

            cards[2].foreignNames!!.forEachIndexed { index, mtgCardForeignInfo ->
                Assert.assertEquals(mtgCardForeignInfo.name, foreignNames[index].name)
                Assert.assertEquals(mtgCardForeignInfo.imageUrl, foreignNames[index].imageUrl)
                Assert.assertEquals(mtgCardForeignInfo.language, foreignNames[index].language)
                Assert.assertEquals(mtgCardForeignInfo.multiverseid, foreignNames[index].multiverseid)
            }
        }
    }
}