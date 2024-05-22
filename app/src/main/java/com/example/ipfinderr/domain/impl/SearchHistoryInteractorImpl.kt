package com.example.ipfinderr.domain.impl

import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.domain.search.SearchHistoryInteractor
import com.example.ipfinderr.domain.search.SearchHistoryRepository

class SearchHistoryInteractorImpl(private val repository: SearchHistoryRepository):
    SearchHistoryInteractor {
    override fun write(input: IpResult) {
        repository.write(input)
    }

    override fun clear() {
        repository.clear()
    }

    override fun read(): List<IpResult> {
        return repository.read()
    }
}