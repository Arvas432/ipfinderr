package com.example.ipfinderr.domain.search


interface SearchHistoryRepository {
    fun write(input: IpResult)
    fun clear()
    fun read(): List<IpResult>
}