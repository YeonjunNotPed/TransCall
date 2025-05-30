package com.youhajun.data.history

import com.youhajun.core.model.filter.DateRangeFilter
import com.youhajun.core.model.pagination.OffsetPageRequest
import com.youhajun.domain.history.HistoryRepository
import javax.inject.Inject

internal class HistoryRepositoryImpl @Inject constructor(
    private val remote: HistoryRemoteDataSource
): HistoryRepository {

    override suspend fun getHistoryList(request: OffsetPageRequest, range: DateRangeFilter?) = runCatching {
        remote.getHistoryList(request, range).toModel { it.toModel() }
    }

    override suspend fun getHistoryDetail(callId: String) = runCatching {
        remote.getHistoryDetail(callId).toModel()
    }

    override suspend fun deleteHistory(callId: String) = runCatching {
        remote.deleteHistory(callId)
    }
}