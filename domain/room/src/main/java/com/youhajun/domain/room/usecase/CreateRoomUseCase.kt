package com.youhajun.domain.room.usecase

import com.youhajun.core.model.RoomInfo
import com.youhajun.domain.room.RoomRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(): Result<RoomInfo> {
        return repository.createRoom()
    }
}
