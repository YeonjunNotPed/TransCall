package com.youhajun.feature.home.impl

import com.youhajun.core.model.CallHistory
import com.youhajun.core.model.MyInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val myInfo: MyInfo = MyInfo(""),
    val callHistoryList: ImmutableList<CallHistory> = persistentListOf(),
    val callHistoryPreviewMaxSize: Int,
    val isShowJoinBottomSheet: Boolean = false,
)