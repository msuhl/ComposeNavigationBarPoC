package dk.isten.andro.composeTabbar.ui.theme.navigation.destinations

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dk.isten.andro.composeTabbar.ui.theme.navigation.NavDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.uri

object LatestDestination: NavDestination {
    override val route = "latest_route"
    override val destination = "latest_destination"
    val deepLink = "$uri/latest"
}

fun NavGraphBuilder.latestGraph(){
    composable(route = LatestDestination.route){
        LatestScreen()
    }
}

@Composable
fun LatestScreen() {
    Text(text = "Latest screen")
}