package com.example.ipfinderr.domain


interface SearchHistoryRepository {
    fun write(input: IpResult)
    fun clear()
    fun read(): List<IpResult>
}