package com.example.ipfinderr.domain.search


interface SearchHistoryInteractor {
    fun write(input: IpResult)
    fun clear()
    fun read(): List<IpResult>
}