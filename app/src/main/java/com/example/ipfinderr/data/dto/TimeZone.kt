package com.example.ipfinderr.data.dto

import com.google.gson.annotations.SerializedName

data class TimeZone (

    @SerializedName("name"              ) var name            : String?   = null,
    @SerializedName("offset"            ) var offset          : Int?      = null,
    @SerializedName("offset_with_dst"   ) var offsetWithDst   : Int?      = null,
    @SerializedName("current_time"      ) var currentTime     : String?   = null,
    @SerializedName("current_time_unix" ) var currentTimeUnix : Double?   = null,
    @SerializedName("is_dst"            ) var isDst           : Boolean?  = null,
    @SerializedName("dst_savings"       ) var dstSavings      : Int?      = null,
    @SerializedName("dst_exists"        ) var dstExists       : Boolean?  = null,
    @SerializedName("dst_start"         ) var dstStart        : DstStart? = DstStart(),
    @SerializedName("dst_end"           ) var dstEnd          : DstEnd?   = DstEnd()

)