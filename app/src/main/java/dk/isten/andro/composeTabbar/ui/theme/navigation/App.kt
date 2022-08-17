package dk.isten.andro.composeTabbar.ui.theme.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import dk.isten.andro.composeTabbar.R
import dk.isten.andro.composeTabbar.ui.theme.ComposeTabBarTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    ComposeTabBarTheme {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    BottomBar(
                        destinations = appState.bottombarDestinations,
                        onNavigateToDestination = appState::navigate,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        ) { padding ->

            NavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,
                onNavigateToDestination = appState::navigate,
                modifier = Modifier
                    .padding(padding)
                    .consumedWindowInsets(padding)
            )

        }
    }
}

@Composable
private fun BottomBar(
    destinations: List<BottomNavigationDestination>,
    onNavigateToDestination: (BottomNavigationDestination) -> Unit,
    currentDestination: NavDestination?
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        NavigationBar(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            )
        ) {
            destinations.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_launcher_foreground,
                            ),
                            contentDescription = ""
                        )
                    }

    //                    {
    //                        val icon = if (selected) {
    //                            destination.selectedIcon
    //                        } else {
    //                            destination.unselectedIcon
    //                        }
    //                        when (icon) {
    //                            is ImageVectorIcon -> Icon(
    //                                imageVector = icon.imageVector,
    //                                contentDescription = null
    //                            )
    //                            is DrawableResourceIcon -> Icon(
    //                                painter = painterResource(id = icon.id),
    //                                contentDescription = null
    //                            )
    //                        }
    //                    }
                    ,
                    label = { Text("Svend") }
                )
            }
        }
    }
}

@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    this@NavigationBarItem.NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor()
        )
    )
}


@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp,
        content = content
    )
}

object NavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}