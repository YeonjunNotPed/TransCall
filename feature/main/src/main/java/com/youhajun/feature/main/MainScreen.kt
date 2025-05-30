package com.youhajun.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.youhajun.feature.call.api.CallNavGraphRegistrar
import com.youhajun.feature.history.api.HistoryNavGraphRegistrar
import com.youhajun.feature.home.api.HomeNavGraphRegistrar
import com.youhajun.feature.main.navigation.MainNavHost
import com.youhajun.feature.main.navigation.MainNavigator
import com.youhajun.feature.main.navigation.MainTab
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun MainScreen(
    mainNavigator: MainNavigator,
    mainTabs: ImmutableList<MainTab>,
    homeNavGraphRegistrar: HomeNavGraphRegistrar,
    callNavGraphRegistrar: CallNavGraphRegistrar,
    historyNavGraphRegistrar: HistoryNavGraphRegistrar,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        bottomBar = {
            MainBottomBar(
                visible = mainNavigator.shouldShowBottomBar(),
                tabs = mainTabs,
                currentTab = mainNavigator.currentTab,
                onTabSelected = { mainNavigator.navigate(it.route) }
            )
        },
    ) { padding ->
        MainNavHost(
            padding = padding,
            navigator = mainNavigator,
            homeNavGraphRegistrar = homeNavGraphRegistrar,
            callNavGraphRegistrar = callNavGraphRegistrar,
            historyNavGraphRegistrar = historyNavGraphRegistrar,
        )
    }
}

@Composable
private fun MainBottomBar(
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(start = 8.dp, end = 8.dp, bottom = 28.dp)
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(size = 28.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tabs.forEach { tab ->
                MainTabItem(
                    tab = tab,
                    selected = tab.route == currentTab?.route,
                    onClick = { onTabSelected(tab) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainTabItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(tab.iconResId),
            contentDescription = null,
            tint = if (selected) {
                Color.Blue
            } else {
                MaterialTheme.colorScheme.outline
            },
            modifier = Modifier.size(34.dp)
        )
    }
}
