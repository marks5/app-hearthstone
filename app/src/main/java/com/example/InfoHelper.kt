package com.example

import com.example.hearthstoneapp.domain.model.InfoFilterEntity

class InfoHelper private constructor() {
    var infoFilterEntity: InfoFilterEntity = InfoFilterEntity()
    var infoFilterList: HashMap<String, List<String>> = hashMapOf()
    var itemSelected: String = "Mage"
    var itemKeySelected: String = "Classes"

    companion object {
        private var INSTANCE: InfoHelper? = null
        fun getInstance(): InfoHelper {
            if (null == INSTANCE) {
                INSTANCE = InfoHelper()
            }
            return INSTANCE!!
        }
    }

    fun setInfoResultValue() {

        val sets = listOf(
            "Basic",
            "Classic",
            "Hall of Fame",
            "Missions",
            "Demo",
            "System",
            "Slush",
            "Promo",
            "Naxxramas",
            "Goblins vs Gnomes",
            "Blackrock Mountain",
            "The Grand Tournament",
            "Credits",
            "Hero Skins",
            "Tavern Brawl",
            "The League of Explorers",
            "Whispers of the Old Gods",
            "Whispers of the Old Gods",
            "One Night in Karazhan",
            "One Night in Karazhan",
            "Mean Streets of Gadgetzan",
            "Mean Streets of Gadgetzan",
            "Journey to Un'Goro",
            "Knights of the Frozen Throne",
            "Kobolds & Catacombs",
            "The Witchwood",
            "The Boomsday Project",
            "Rastakhan's Rumble",
            "Rise of Shadows",
            "Taverns of Time",
            "Saviors of Uldum",
            "Descent of Dragons",
            "Galakrond's Awakening",
            "Ashes of Outland",
            "Wild Event",
            "Scholomance Academy",
            "Battlegrounds",
            "Demon Hunter Initiate",
            "Madness at the Darkmoon Faire",
            "Forged in the Barrens",
            "Legacy",
            "Core",
            "Classic",
            "Wailing Caverns",
            "United in Stormwind",
            "Mercenaries",
            "Fractured in Alterac Valley",
            "Voyage to the Sunken City",
            "Unknown",
            "Murder at Castle Nathria",
            "March of the Lich King",
            "Path of Arthas"
        )

        val standard = listOf(
            "Core",
            "Forged in the Barrens",
            "United in Stormwind",
            "Fractured in Alterac Valley",
            "Voyage to the Sunken City",
            "Murder at Castle Nathria",
            "March of the Lich King",
            "Path of Arthas"
        )

        val wild = listOf(
            "Legacy",
            "Promo",
            "Naxxramas",
            "Goblins vs Gnomes",
            "Blackrock Mountain",
            "The Grand Tournament",
            "The League of Explorers",
            "Whispers of the Old Gods",
            "Whispers of the Old Gods",
            "One Night in Karazhan",
            "One Night in Karazhan",
            "Mean Streets of Gadgetzan",
            "Mean Streets of Gadgetzan",
            "Journey to Un'Goro",
            "Knights of the Frozen Throne",
            "Kobolds & Catacombs",
            "The Witchwood",
            "The Boomsday Project",
            "Rastakhan's Rumble",
            "Rise of Shadows",
            "Saviors of Uldum",
            "Descent of Dragons",
            "Galakrond's Awakening",
            "Ashes of Outland",
            "Demon Hunter Initiate",
            "Scholomance Academy",
            "Madness at the Darkmoon Faire",
            "Forged in the Barrens",
            "United in Stormwind",
            "Fractured in Alterac Valley",
            "Voyage to the Sunken City",
            "Murder at Castle Nathria",
            "March of the Lich King",
            "Path of Arthas"
        )

        val types = listOf(
            "Hero",
            "Minion",
            "Spell",
            "Enchantment",
            "Weapon",
            "Hero Power",
            "Location"
        )

        val factions = listOf(
            "Horde",
            "Alliance",
            "Neutral"
        )

        val qualities = listOf(
            "Common",
            "Free",
            "Rare",
            "Epic",
            "Legendary"
        )

        val races = listOf(
            "Orc",
            "Undead",
            "Murloc",
            "Demon",
            "Mech",
            "Elemental",
            "Beast",
            "Totem",
            "Pirate",
            "Dragon",
            "All",
            "Quilboar",
            "Naga"
        )

        val classes = listOf(
            "Death Knight",
            "Druid",
            "Hunter",
            "Mage",
            "Paladin",
            "Priest",
            "Rogue",
            "Shaman",
            "Warlock",
            "Warrior",
            "Dream",
            "Neutral",
            "Whizbang",
            "Demon Hunter"
        )

        val entity = InfoFilterEntity(
            factions = factions,
            standard = standard,
            classes = classes,
            sets = sets,
            wild = wild,
            qualities = qualities,
            races = races,
            types = types,
            locales = listOf(
                "deDE",
                "enGB",
                "enUS",
                "esES",
                "esMX",
                "frFR",
                "itIT",
                "koKR",
                "plPL",
                "ptBR",
                "ruRU",
                "zhCN",
                "zhTW",
                "jaJP",
                "thTH"
            )
        )

        this.infoFilterEntity = entity
    }

    private fun getListInfoFilters(): List<String> =
        listOf("Classes", "Sets", "Types", "Factions", "Qualities", "Races", "Locales")

    fun setItemClicked(itemSelected: String) {
        this.itemSelected = itemSelected
    }

    fun getItemClicked(): String {
        return itemSelected
    }

    fun setItemKeyClicked(itemKeySelected: String) {
        this.itemKeySelected = itemKeySelected
    }

    fun getItemKeyClicked(): String {
        return itemKeySelected
    }

    fun getInfoListObjects(): Map<String, List<String>> {
        this.infoFilterList = mapOf(
            Pair(getListInfoFilters()[0], infoFilterEntity.classes),
            Pair(getListInfoFilters()[1], infoFilterEntity.sets),
            Pair(getListInfoFilters()[2], infoFilterEntity.types),
            Pair(getListInfoFilters()[3], infoFilterEntity.factions),
            Pair(getListInfoFilters()[4], infoFilterEntity.qualities),
            Pair(getListInfoFilters()[5], infoFilterEntity.races),
            Pair(getListInfoFilters()[6], infoFilterEntity.locales)
        ) as HashMap<String, List<String>>

        return infoFilterList
    }
}