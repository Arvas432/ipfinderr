package com.example.ipfinderr


interface IpInteractor {
    fun searchIp(expression: String, consumer: IpConsumer)
    interface IpConsumer{
        fun consume(searchResult: IpResult)
    }
}