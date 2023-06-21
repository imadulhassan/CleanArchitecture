package com.sample.models


import com.google.gson.annotations.SerializedName



data class Maintainer(
    @SerializedName("github")
    val github: String
)