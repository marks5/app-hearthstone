package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharByClassEntity (
    @SerializedName("cardId") val cardIdFromChar: String,
    @SerializedName("dbfId") val cardDbfIdFromChar: Long,
    @SerializedName("name") val cardNameFromChar: String,
    @SerializedName("cardSet") val cardSetFromChar: String,
    @SerializedName("type") val cardTypeFromChar: String,
    @SerializedName("health") val cardHealthFromChar: Int,
    @SerializedName("text") val cardTextFromChar: String,
    @SerializedName("artist") val cardArtistsFromChar: String,
    @SerializedName("playerClass") val cardPlayerClassFromChar: String,
    @SerializedName("locale") val cardLocaleFromChar: String
)
