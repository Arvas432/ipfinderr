package com.example.ipfinderr


interface IpRepository {
    fun searchIp(expression: String): IpResult
}