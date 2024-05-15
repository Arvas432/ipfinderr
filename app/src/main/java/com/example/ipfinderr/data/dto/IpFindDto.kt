package com.example.ipfinderr.data.dto

import com.google.gson.annotations.SerializedName

data class IpFindDto (

    @SerializedName("ip"                    ) var ip                  : String?   = null,
    @SerializedName("continent_code"        ) var continentCode       : String?   = null,
    @SerializedName("continent_name"        ) var continentName       : String?   = null,
    @SerializedName("country_code2"         ) var countryCode2        : String?   = null,
    @SerializedName("country_code3"         ) var countryCode3        : String?   = null,
    @SerializedName("country_name"          ) var countryName         : String?   = null,
    @SerializedName("country_name_official" ) var countryNameOfficial : String?   = null,
    @SerializedName("country_capital"       ) var countryCapital      : String?   = null,
    @SerializedName("state_prov"            ) var stateProv           : String?   = null,
    @SerializedName("state_code"            ) var stateCode           : String?   = null,
    @SerializedName("district"              ) var district            : String?   = null,
    @SerializedName("city"                  ) var city                : String?   = null,
    @SerializedName("zipcode"               ) var zipcode             : String?   = null,
    @SerializedName("latitude"              ) var latitude            : String?   = null,
    @SerializedName("longitude"             ) var longitude           : String?   = null,
    @SerializedName("is_eu"                 ) var isEu                : Boolean?  = null,
    @SerializedName("calling_code"          ) var callingCode         : String?   = null,
    @SerializedName("country_tld"           ) var countryTld          : String?   = null,
    @SerializedName("languages"             ) var languages           : String?   = null,
    @SerializedName("country_flag"          ) var countryFlag         : String?   = null,
    @SerializedName("geoname_id"            ) var geonameId           : String?   = null,
    @SerializedName("isp"                   ) var isp                 : String?   = null,
    @SerializedName("connection_type"       ) var connectionType      : String?   = null,
    @SerializedName("organization"          ) var organization        : String?   = null,
    @SerializedName("country_emoji"         ) var countryEmoji        : String?   = null,
    @SerializedName("currency"              ) var currency            : Currency? = Currency(),
    @SerializedName("time_zone"             ) var timeZone            : TimeZone? = TimeZone()

)