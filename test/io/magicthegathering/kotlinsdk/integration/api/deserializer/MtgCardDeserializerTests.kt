package io.magicthegathering.kotlinsdk.api.deserializer

import com.google.gson.JsonParser
import org.junit.Assert.assertEquals
import org.junit.Test

class MtgCardDeserializerTests {

    @Test
    fun parseStringLoyalty() {
        // given https://api.magicthegathering.io/v1/cards/?multiverseid=426906
        val stringNissaCard: String = "{\n" +
                "      \"name\": \"Nissa, Steward of Elements\",\n" +
                "      \"manaCost\": \"{X}{G}{U}\",\n" +
                "      \"cmc\": 2,\n" +
                "      \"colors\": [\n" +
                "        \"Green\",\n" +
                "        \"Blue\"\n" +
                "      ],\n" +
                "      \"colorIdentity\": [\n" +
                "        \"G\",\n" +
                "        \"U\"\n" +
                "      ],\n" +
                "      \"type\": \"Legendary Planeswalker — Nissa\",\n" +
                "      \"supertypes\": [\n" +
                "        \"Legendary\"\n" +
                "      ],\n" +
                "      \"types\": [\n" +
                "        \"Planeswalker\"\n" +
                "      ],\n" +
                "      \"subtypes\": [\n" +
                "        \"Nissa\"\n" +
                "      ],\n" +
                "      \"rarity\": \"Mythic\",\n" +
                "      \"set\": \"AKH\",\n" +
                "      \"setName\": \"Amonkhet\",\n" +
                "      \"text\": \"+2: Scry 2.\\n0: Look at the top card of your library. If it's a land card or a creature card with converted mana cost less than or equal to the number of loyalty counters on Nissa, Steward of Elements, you may put that card onto the battlefield.\\n−6: Untap up to two target lands you control. They become 5/5 Elemental creatures with flying and haste until end of turn. They're still lands.\",\n" +
                "      \"artist\": \"Howard Lyon\",\n" +
                "      \"number\": \"204\",\n" +
                "      \"layout\": \"normal\",\n" +
                "      \"multiverseid\": 426906,\n" +
                "      \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=426906&type=card\",\n" +
                "      \"loyalty\": \"X\",\n" +
                "      \"rulings\": [\n" +
                "        {\n" +
                "          \"date\": \"2017-04-18\",\n" +
                "          \"text\": \"If X is 0, Nissa enters the battlefield with no loyalty and is put into her owner’s graveyard before you can activate her abilities.\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"date\": \"2017-04-18\",\n" +
                "          \"text\": \"While resolving Nissa’s second ability, if you can’t or don’t put the card onto the battlefield, it remains on top of your library.\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"date\": \"2017-04-18\",\n" +
                "          \"text\": \"Once Nissa’s second ability begins to resolve, no player may take actions until it’s done. Notably, players can’t try to change Nissa’s loyalty once you look at the card or choose to put it onto the battlefield.\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"date\": \"2017-04-18\",\n" +
                "          \"text\": \"If Nissa leaves the battlefield while her second ability is on the stack, use her last known loyalty to determine whether you may put the top card of your library onto the battlefield. If Nissa left the battlefield because she took enough damage to reduce her loyalty to 0, then her last known loyalty is 0.\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"date\": \"2017-04-18\",\n" +
                "          \"text\": \"Nissa’s last ability sets the base power and toughness of the lands to 5/5. Any +1/+1 or -1/-1 counters that were on those lands will apply to those values.\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"foreignNames\": [\n" +
                "        {\n" +
                "          \"name\": \"Nissa, Hüterin der Elemente\",\n" +
                "          \"text\": \"+2: Hellsicht 2.\\n0: Schaue dir die oberste Karte deiner Bibliothek an. Falls es eine Länderkarte oder eine Kreaturenkarte mit umgewandelten Manakosten kleiner oder gleich der Anzahl an Loyalitätsmarken auf Nissa, Hüterin der Elemente, ist, kannst du die Karte ins Spiel bringen.\\n−6: Enttappe bis zu zwei Länder deiner Wahl, die du kontrollierst. Sie werden bis zum Ende des Zuges zu 5/5 Elementarwesen-Kreaturen mit Flugfähigkeit und Eile. Sie sind auch immer noch Länder.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=427175&type=card\",\n" +
                "          \"language\": \"German\",\n" +
                "          \"multiverseid\": 427175\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Nissa, encarnación de los elementos\",\n" +
                "          \"text\": \"+2: Adivina 2.\\n0: Mira la primera carta de tu biblioteca. Si es una carta de tierra o de criatura con un coste de maná convertido menor o igual a la cantidad de contadores de lealtad sobre Nissa, encarnación de los elementos, puedes poner esa carta en el campo de batalla.\\n−6: Endereza hasta dos tierras objetivo que controlas. Se convierten en criaturas Elementales 5/5 con las habilidades de volar y prisa hasta el final del turno. Siguen siendo tierras.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=427444&type=card\",\n" +
                "          \"language\": \"Spanish\",\n" +
                "          \"multiverseid\": 427444\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Nissa, intendante des éléments\",\n" +
                "          \"text\": \"+2 : Regard 2.\\n0 : Regardez la carte du dessus de votre bibliothèque. Si c'est une carte de terrain ou de créature avec un coût converti de mana inférieur ou égal au nombre de marqueurs « loyauté » sur Nissa, intendante des éléments, vous pouvez mettre cette carte sur le champ de bataille.\\n-6 : Dégagez jusqu'à deux terrains ciblés que vous contrôlez. Ils deviennent des créatures 5/5 Élémental avec le vol et la célérité jusqu'à la fin du tour. Ce sont toujours des terrains.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=427713&type=card\",\n" +
                "          \"language\": \"French\",\n" +
                "          \"multiverseid\": 427713\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Nissa, Guardiana degli Elementi\",\n" +
                "          \"text\": \"+2: Profetizza 2.\\n0: Guarda la prima carta del tuo grimorio. Se è una carta terra o una carta creatura con costo di mana convertito pari o inferiore al numero di segnalini fedeltà su Nissa, Guardiana degli Elementi, puoi mettere quella carta sul campo di battaglia.\\n-6: STAPpa fino a due terre bersaglio che controlli. Diventano creature Elementale 5/5 con volare e rapidità fino alla fine del turno. Sono ancora terre.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=427982&type=card\",\n" +
                "          \"language\": \"Italian\",\n" +
                "          \"multiverseid\": 427982\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"自然に仕える者、ニッサ\",\n" +
                "          \"text\": \"+2：占術２を行う。\\n0：あなたのライブラリーの一番上のカードを見る。それが土地・カードであるか、点数で見たマナ・コストが自然に仕える者、ニッサの上に置かれている忠誠カウンターの総数以下であるクリーチャー・カードであるなら、あなたはそのカードを戦場に出してもよい。\\n-6：あなたがコントロールする土地最大２つを対象とし、それらをアンタップする。ターン終了時まで、それらは飛行と速攻を持つ５/５のエレメンタル・クリーチャーになる。それらは土地でもある。\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=428251&type=card\",\n" +
                "          \"language\": \"Japanese\",\n" +
                "          \"multiverseid\": 428251\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"원소의 관리인 니사\",\n" +
                "          \"text\": \"+2: 점술 2를 한다.\\n0: 당신의 서고 맨 위의 카드를 본다. 그 카드가 대지 카드 또는 생물 카드이고, 전환마나비용이 원소의 관리인 니사에 올려진 충성 카운터 개수보다 적거나 같다면, 당신은 그 카드를 전장에 놓을 수 있다.\\n−6: 당신이 조종하는 대지를 최대 두 개까지 목표로 정한다. 그 대지들을 언탭한다. 그 대지들은 턴종료까지 비행과 신속을 가진 5/5 정령 생물이 된다. 그 대지들은 생물이 된 이후에도 여전히 대지이다.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=428520&type=card\",\n" +
                "          \"language\": \"Korean\",\n" +
                "          \"multiverseid\": 428520\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Nissa, Guardiã dos Elementos\",\n" +
                "          \"text\": \"+2: Use vidência 2.\\n0: Olhe o card do topo de seu grimório. Se for um card de terreno ou de criatura com custo de mana convertido igual ou inferior ao número de marcadores de lealdade em Nissa, Guardiã dos Elementos, você poderá colocar aquele card no campo de batalha.\\n−6: Desvire até dois terrenos alvo que você controla. Eles se tornam criaturas 5/5 do tipo Elemental com voar e ímpeto até o final do turno. Elas ainda são terrenos.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=428789&type=card\",\n" +
                "          \"language\": \"Portuguese (Brazil)\",\n" +
                "          \"multiverseid\": 428789\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Нисса, Проводница Стихий\",\n" +
                "          \"text\": \"+2: предскажите 2.\\n0: посмотрите верхнюю карту вашей библиотеки. Если это карта земли или карта существа с конвертированной мана-стоимостью, меньшей или равной количеству жетонов верности на Ниссе, Проводнице Стихий, то вы можете положить ту карту на поле битвы.\\n−6: разверните не более двух целевых земель под вашим контролем. Они становятся существами 5/5 Элементаль с Полетом и Ускорением до конца хода. При этом они остаются землями.\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=429058&type=card\",\n" +
                "          \"language\": \"Russian\",\n" +
                "          \"multiverseid\": 429058\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"地源守护妮莎\",\n" +
                "          \"text\": \"+2：占卜2。\\n0：检视你的牌库顶牌。如果该牌是地牌或总法术力费用等于或小于地源守护妮莎上忠诚指示物数量的生物牌，则你可以将该牌放进战场。\\n−6：重置至多两个目标由你操控的地。它们成为5/5，具飞行与敏捷异能的元素生物直到回合结束。它们仍然是地。\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=429327&type=card\",\n" +
                "          \"language\": \"Chinese Simplified\",\n" +
                "          \"multiverseid\": 429327\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"地源守護妮莎\",\n" +
                "          \"text\": \"+2：占卜2。\\n0：檢視你的牌庫頂牌。如果該牌是地牌或總魔法力費用等於或小於地源守護妮莎上忠誠指示物數量的生物牌，則你可以將該牌放進戰場。\\n−6：重置至多兩個目標由你操控的地。它們成為5/5，具飛行與敏捷異能的元素生物直到回合結束。它們仍然是地。\",\n" +
                "          \"flavor\": null,\n" +
                "          \"imageUrl\": \"http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=429596&type=card\",\n" +
                "          \"language\": \"Chinese Traditional\",\n" +
                "          \"multiverseid\": 429596\n" +
                "        }\n" +
                "      ],\n" +
                "      \"printings\": [\n" +
                "        \"AKH\",\n" +
                "        \"PAKH\",\n" +
                "        \"PS17\"\n" +
                "      ],\n" +
                "      \"originalText\": \"+2: Scry 2.\\n0: Look at the top card of your library. If it's a land card or a creature card with converted mana cost less than or equal to the number of loyalty counters on Nissa, Steward of Elements, you may put that card onto the battlefield.\\n−6: Untap up to two target lands you control. They become 5/5 Elemental creatures with flying and haste until end of turn. They're still lands.\",\n" +
                "      \"originalType\": \"Planeswalker - Nissa\",\n" +
                "      \"legalities\": [\n" +
                "        {\n" +
                "          \"format\": \"Commander\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"format\": \"Duel\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"format\": \"Frontier\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"format\": \"Legacy\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"format\": \"Modern\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"format\": \"Vintage\",\n" +
                "          \"legality\": \"Legal\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"id\": \"61a8d226-c742-5393-b41a-56f18eb9333f\"\n" +
                "    }"
        val jsonNissaCard = JsonParser().parse(stringNissaCard).asJsonObject

        // when
        val nissaCard = MtgCardDeserializer().deserialize(jsonNissaCard, null, null)

        // then
        assertEquals("X", nissaCard!!.loyalty)
    }
}