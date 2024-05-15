package com.example.ipfinderr.domain


interface IpRepository {
    fun searchIp(expression: String): IpResult
}