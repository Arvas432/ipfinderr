package com.example.ipfinderr.data.dto

import com.google.gson.annotations.SerializedName

data class DstEnd (

    @SerializedName("utc_time"       ) var utcTime        : String?  = null,
    @SerializedName("duration"       ) var duration       : String?  = null,
    @SerializedName("gap"            ) var gap            : Boolean? = null,
    @SerializedName("dateTimeAfter"  ) var dateTimeAfter  : String?  = null,
    @SerializedName("dateTimeBefore" ) var dateTimeBefore : String?  = null,
    @SerializedName("overlap"        ) var overlap        : Boolean? = null

)
