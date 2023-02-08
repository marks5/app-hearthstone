package com.example.hearthstoneapp.presentation.viewmodel

import com.example.hearthstoneapp.domain.model.InfoFilterEntity
import com.example.hearthstoneapp.domain.model.Locales
import com.example.hearthstoneapp.domain.useCase.CardDataUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val cardDataUseCase = mockk<CardDataUseCase>()
    private val infoFilterEntity = mockk<InfoFilterEntity>()

    @Test
    fun `When MainViewModel call get info should start and stop loading then return InfoFilterEntity`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = MainViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getCardFiltersData() } returns flow { infoFilterEntity }
            viewModelMocked.getInfo()
            assertThat(viewModelMocked.uiStateLoading.value).isEqualTo(InfoUiState.Loading(false))
            advanceUntilIdle()

            coVerify { cardDataUseCase.getCardFiltersData() }
            assertThat(viewModelMocked.uiStateLoading.value).isEqualTo(InfoUiState.Loading(true))
        }

    private val infoFilterEntityMocked = InfoFilterEntity(
        patch = "25.2.2.163976",
        classes = listOf(
            "Death Knight", "Druid",
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
        ),
        sets = listOf(
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
        ),
        standard = listOf(
            "Core",
            "Forged in the Barrens",
            "United in Stormwind",
            "Fractured in Alterac Valley",
            "Voyage to the Sunken City",
            "Murder at Castle Nathria",
            "March of the Lich King",
            "Path of Arthas"
        ),
        wild = listOf(
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
        ),
        types = listOf(
            "Hero",
            "Minion",
            "Spell",
            "Enchantment",
            "Weapon",
            "Hero Power",
            "Location"
        ),
        factions = listOf(
            "Horde",
            "Alliance",
            "Neutral"
        ),
        qualities = listOf(
            "Common",
            "Free",
            "Rare",
            "Epic",
            "Legendary"
        ),
        races = listOf(
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
        ),
        locales = Locales(
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

    @Test
    fun `When MainViewModel call getInfo and has success should return InfoFilterEntity`() =
        runTest {

            val dispatcher = StandardTestDispatcher(testScheduler)
            val viewModelMocked = MainViewModel(cardDataUseCase, dispatcher)

            coEvery { cardDataUseCase.getCardFiltersData() } returns flow {
                emit(
                    infoFilterEntityMocked
                )
            }
            viewModelMocked.getInfo()
            assertThat(viewModelMocked.uiStateSuccess.value).isEqualTo(
                InfoUiState.Success(
                    InfoFilterEntity()
                )
            )
            advanceUntilIdle()

            coVerify { cardDataUseCase.getCardFiltersData() }
            assertThat(viewModelMocked.uiStateSuccess.value).isEqualTo(
                InfoUiState.Success(
                    infoFilterEntityMocked
                )
            )
        }
}
