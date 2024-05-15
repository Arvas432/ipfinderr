package com.example.ipfinderr.data.localstorage.impl
import com.example.ipfinderr.data.localstorage.LocalIpStorageHandler
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.domain.SearchHistoryRepository


class SearchHistoryRepositoryImpl(private val localTrackStorageHandler: LocalIpStorageHandler):
    SearchHistoryRepository {
    override fun write(input: IpResult) {
       localTrackStorageHandler.write(input)
    }

    override fun clear() {
        localTrackStorageHandler.clear()
    }

    override fun read(): List<IpResult> {
        return localTrackStorageHandler.read()
    }
}