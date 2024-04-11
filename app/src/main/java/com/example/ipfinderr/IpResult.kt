package com.example.ipfinderr

data class IpResult(val ip: String,
                    val country: String,
                    val city: String,
                    val isp: String,
                    val latitude: String,
                    val longitude: String,
                    val countryCode: String,
                    val district: String,
                    val zipcode: String,
                    val timezone: List<String>,
                    val currency: List<String>)
