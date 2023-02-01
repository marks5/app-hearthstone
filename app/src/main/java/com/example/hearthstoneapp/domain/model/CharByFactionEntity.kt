package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharByFactionEntity(
    @SerializedName("cardId") val cardIdFromChar: String,
    @SerializedName("name") val nameFromChar: String,
    @SerializedName("cardSet") val cardSetFromChar: String,
    @SerializedName("type") val typeFromChar: String,
    @SerializedName("text") val textFromChar: String,
    @SerializedName("playerClass") val playerClassFromChar: String,
    @SerializedName("locale") val localeFromChar: String
)
