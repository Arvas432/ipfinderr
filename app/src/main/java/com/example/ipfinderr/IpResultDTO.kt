package com.example.ipfinderr

data class IpResultDTO(
    val ip: String?,
    val country_name: String?,
    val city: String?,
    val isp: String?,
    val latitude: String?,
    val longitude: String?,
    val country_code2: String?,
    val district: String?,
    val zipcode: String?,
    val timezone: List<String>?,
    val currency: List<String>?
)
