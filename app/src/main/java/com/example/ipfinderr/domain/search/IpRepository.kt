package com.example.ipfinderr.domain.search


interface IpRepository {
    fun searchIp(expression: String): IpResult
}