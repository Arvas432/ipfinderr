package com.example.ipfinderr.domain.search


interface IpInteractor {
    fun searchIp(expression: String, consumer: IpConsumer)
    interface IpConsumer{
        fun consume(searchResult: IpSearchResult)
    }
}