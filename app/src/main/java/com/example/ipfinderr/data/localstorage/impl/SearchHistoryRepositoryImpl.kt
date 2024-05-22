package com.example.ipfinderr.data.localstorage.impl
import com.example.ipfinderr.data.localstorage.LocalIpStorageHandler
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.domain.search.SearchHistoryRepository


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