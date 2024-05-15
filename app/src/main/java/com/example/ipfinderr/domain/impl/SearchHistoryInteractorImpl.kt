package com.example.ipfinderr.domain.impl

import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.domain.SearchHistoryInteractor
import com.example.ipfinderr.domain.SearchHistoryRepository

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