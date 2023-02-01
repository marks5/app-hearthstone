package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharByRaceEntity(
    @SerializedName("cardId")
    val cardID: String,
    @SerializedName("dbfId")
    val dbfID: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("cardSet")
    val cardSet: String,
    @SerializedName("type")
    val type: Type? = null,
    @SerializedName("faction")
    val faction: Faction? = null,
    @SerializedName("health")
    val health: Long? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("race")
    val race: Race,
    @SerializedName("playerClass")
    val playerClass: Faction? = null,
    @SerializedName("locale")
    val locale: Locale,
    @SerializedName("rarity")
    val rarity: Rarity? = null,
    @SerializedName("cost")
    val cost: Long? = null,
    @SerializedName("attack")
    val attack: Long? = null,
    @SerializedName("elite")
    val elite: Boolean? = null,
    @SerializedName("img")
    val img: String? = null,
    @SerializedName("mechanics")
    val mechanics: List<Mechanic>? = null,
    @SerializedName("artist")
    val artist: String? = null,
    @SerializedName("imgGold")
    val imgGold: String? = null,
    @SerializedName("flavor")
    val flavor: String? = null,
    @SerializedName("collectible")
    val collectible: Boolean? = null,
    @SerializedName("howToGet")
    val howToGet: String? = null,
    @SerializedName("howToGetGold")
    val howToGetGold: String? = null,
    @SerializedName("otherRaces")
    val otherRaces: List<String>? = null,
    @SerializedName("spellSchool")
    val spellSchool: String? = null
)

enum class Faction(val value: String) {
    DemonHunter("Demon Hunter"),
    Dream("Dream"),
    Druid("Druid"),
    Hunter("Hunter"),
    Mage("Mage"),
    Neutral("Neutral"),
    Paladin("Paladin"),
    Priest("Priest"),
    Rogue("Rogue"),
    Shaman("Shaman"),
    Warlock("Warlock"),
    Warrior("Warrior");

    companion object {
        public fun fromValue(value: String): Faction = when (value) {
            "Demon Hunter" -> DemonHunter
            "Dream" -> Dream
            "Druid" -> Druid
            "Hunter" -> Hunter
            "Mage" -> Mage
            "Neutral" -> Neutral
            "Paladin" -> Paladin
            "Priest" -> Priest
            "Rogue" -> Rogue
            "Shaman" -> Shaman
            "Warlock" -> Warlock
            "Warrior" -> Warrior
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Locale(val value: String) {
    EnUS("enUS");

    companion object {
        public fun fromValue(value: String): Locale = when (value) {
            "enUS" -> EnUS
            else -> throw IllegalArgumentException()
        }
    }
}

data class Mechanic(
    val name: String
)

enum class Race(val value: String) {
    Dragon("Dragon");

    companion object {
        public fun fromValue(value: String): Race = when (value) {
            "Dragon" -> Dragon
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Rarity(val value: String) {
    Common("Common"),
    Epic("Epic"),
    Legendary("Legendary"),
    Rare("Rare");

    companion object {
        public fun fromValue(value: String): Rarity = when (value) {
            "Common" -> Common
            "Epic" -> Epic
            "Legendary" -> Legendary
            "Rare" -> Rare
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Type(val value: String) {
    Hero("Hero"),
    Minion("Minion");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "Hero" -> Hero
            "Minion" -> Minion
            else -> throw IllegalArgumentException()
        }
    }
}