package com.example.ipfinderr.data.dto

import com.google.gson.annotations.SerializedName

data class Currency (

    @SerializedName("code"   ) var code   : String? = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("symbol" ) var symbol : String? = null

)