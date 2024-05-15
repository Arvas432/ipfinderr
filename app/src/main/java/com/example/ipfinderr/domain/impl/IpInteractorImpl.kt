package com.example.ipfinderr.domain.impl

import com.example.ipfinderr.domain.IpInteractor
import com.example.ipfinderr.domain.IpRepository
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.domain.IpSearchResult
import com.example.ipfinderr.domain.SearchResultType
import java.util.concurrent.Executors

class IpInteractorImpl(private val repository: IpRepository): IpInteractor {
    private val executor = Executors.newCachedThreadPool()
    override fun searchIp(expression: String, consumer: IpInteractor.IpConsumer) {
        executor.execute {
            if(expression.isNotEmpty()){
                val emptyResult = IpResult("", "", "", "", "", "", "", "", "","")
                consumer.consume(IpSearchResult(emptyResult, SearchResultType.LOADING))
                try {
                    val result = repository.searchIp(expression)
                    if(result.ip!=""){
                        consumer.consume(IpSearchResult(result, SearchResultType.SUCCESS))
                    }else{
                        consumer.consume(IpSearchResult(emptyResult, SearchResultType.EMPTY))
                    }
                }catch (e: Throwable){
                    e.printStackTrace()
                    consumer.consume(IpSearchResult(emptyResult, SearchResultType.ERROR))
                }

            }

        }
    }

}