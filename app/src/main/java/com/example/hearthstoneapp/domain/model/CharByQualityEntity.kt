package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharByQualityEntity(
    @SerializedName("cardId") val cardIdFromChar: String,
    @SerializedName("dbfId") val dbfIdFromChar: String,
    @SerializedName("name") val nameFromChar: String,
    @SerializedName("cardSet") val cardSetFromChar: String,
    @SerializedName("type") val typeFromChar: String,
    @SerializedName("cost") val costFromChar: Int,
    @SerializedName("attack") val attackFromChar: Int,
    @SerializedName("health") val healthFromChar: Int,
    @SerializedName("text") val textFromChar: String,
    @SerializedName("race") val raceFromChar: String,
    @SerializedName("locale") val localeFromChar: String,
)