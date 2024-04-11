package com.example.ipfinderr

import android.util.Log
import java.util.concurrent.Executors

class IpInteractorImpl(private val repository: IpRepository): IpInteractor {
    private val executor = Executors.newCachedThreadPool()
    override fun searchIp(expression: String, consumer: IpInteractor.IpConsumer) {
        executor.execute {
            if(expression.isNotEmpty()){
                try {
                    val ip = repository.searchIp(expression)
                    if(ip != null){
                        consumer.consume(ip)
                    }else{
                        Log.i("INTERACTOR", "IM HERE")
                    }
                }catch (e: Throwable){
                    e.printStackTrace()
                }

            }

        }
    }

}