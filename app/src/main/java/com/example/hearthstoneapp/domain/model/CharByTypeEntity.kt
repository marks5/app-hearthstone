package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharByTypeEntity(
    @SerializedName("cardId") val cardIdFromChar: String,
    @SerializedName("dbfId") val dbfIdFromChar: String,
    @SerializedName("name") val nameFromChar: String,
    @SerializedName("cardSet") val cardSetFromChar: String,
    @SerializedName("type") val typeFromChar: String,
    @SerializedName("cost") val costFromChar: Int,
    @SerializedName("text") val textFromChar: String,
    @SerializedName("playerClass") val playerClassFromChar: String,
    @SerializedName("locale") val localeFromChar: String,
    @SerializedName("mechanics") val mechanicsFromChar: HashMap<String, MechanicsEntity>
)

