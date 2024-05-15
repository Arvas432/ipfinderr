package com.example.ipfinderr.domain

data class IpResult(val ip: String,
                    val country: String,
                    val city: String,
                    val isp: String,
                    val latitude: String,
                    val longitude: String,
                    val countryCode: String,
                    val district: String,
                    val zipcode: String,
                    val imageUrl: String,
                    val timeZone: String,
                    val currency: String)
