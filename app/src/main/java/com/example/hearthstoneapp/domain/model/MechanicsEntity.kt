package com.example.hearthstoneapp.domain.model

import com.google.gson.annotations.SerializedName

data class MechanicsEntity(
    @SerializedName("name") val nameFromMechanics: String
)