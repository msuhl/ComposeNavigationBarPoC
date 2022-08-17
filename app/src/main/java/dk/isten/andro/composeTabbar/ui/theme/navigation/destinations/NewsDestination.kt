package dk.isten.andro.composeTabbar.ui.theme.navigation.destinations

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dk.isten.andro.composeTabbar.ui.theme.navigation.NavDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.uri

object NewsDestination:NavDestination {
    override val route = "news_route"
    override val destination = "news_destination"
    val deepLink = "$uri/news"
}

fun NavGraphBuilder.newsGraph(){
    composable(route = NewsDestination.route){
        NewsScreen()
    }
}

@Composable
fun NewsScreen() {
    Text(text = "News screen")
}
