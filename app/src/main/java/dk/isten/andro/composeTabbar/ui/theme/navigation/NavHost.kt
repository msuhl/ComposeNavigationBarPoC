package dk.isten.andro.composeTabbar.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.*
import dk.isten.andro.composeTabbar.ui.theme.navigation.destinations.ArticleDestination.articleGraph

val uri = "dk.tv2.news"

@Composable
fun NavHost(
    navController: NavHostController,
    onNavigateToDestination: (NavDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = NewsDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        newsGraph()
        latestGraph()
        mostreadGraph()
        articleGraph()
    }
}