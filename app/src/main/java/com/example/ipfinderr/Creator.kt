package com.example.ipfinderr
object Creator {
    private fun getIpRepository(): IpRepository{
        return IpRepositoryImpl(RetrofitNetworkClient())
    }
    fun provideIpInteractor(): IpInteractor {
        return IpInteractorImpl(getIpRepository())
    }
}