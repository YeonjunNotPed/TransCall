package com.youhajun.data.room.dto

import com.youhajun.core.model.LanguageType
import com.youhajun.core.model.Participant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParticipantDto(
    @SerialName("userId")
    val userId: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("language")
    val language: String = "",
    @SerialName("isHost")
    val isHost: Boolean = false
) {
    fun toModel(): Participant = Participant(
        userId = userId,
        displayName = displayName,
        imageUrl = imageUrl,
        isHost = isHost,
        language = LanguageType.fromCode(language),
    )
}