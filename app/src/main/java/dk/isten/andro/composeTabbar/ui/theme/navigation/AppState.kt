package dk.isten.andro.composeTabbar.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.ArticleDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.LatestDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.MostReadDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.NewsDestination


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController) {
        AppState(navController = navController)
    }
}

@Stable
class AppState(
    val navController: NavHostController,
) {

    val shouldShowBottomBar: Boolean = true
    val bottombarDestinations:List<BottomNavigationDestination> = listOf(
        BottomNavigationDestination(
            NewsDestination.route,
            NewsDestination.destination ,
            NewsDestination.deepLink),
        BottomNavigationDestination(LatestDestination.route, LatestDestination.destination, LatestDestination.deepLink ),
        BottomNavigationDestination(MostReadDestination.route, MostReadDestination.destination,MostReadDestination.deepLink ),
    )

    val inAppDestinations:List<TeaserNavigationDestination> = listOf(
        TeaserNavigationDestination(ArticleDestination.route, ArticleDestination.destination, ArticleDestination.deepLink)
    )

    val currentDestination: androidx.navigation.NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    fun navigate(destination: NavDestination, route: String? = null) {
            if (destination is BottomNavigationDestination) {
                navController.navigate(route ?: destination.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.route)
            }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}


data class BottomNavigationDestination(
    override val route: String,
    override val destination: String,
    val deepLink: String,
) : NavDestination

data class TeaserNavigationDestination(
    override val route: String,
    override val destination: String,
    val deepLink: String,
) : NavDestination