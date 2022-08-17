package dk.isten.andro.composeTabbar.ui.theme.navigation.destinations

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dk.isten.andro.composeTabbar.ui.theme.navigation.NavDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.uri

object MostReadDestination: NavDestination {
    override val route = "mostread_route"
    override val destination = "mostread_destination"
    val deepLink = "$uri/mostread"
}

fun NavGraphBuilder.mostreadGraph(){
    composable(route = MostReadDestination.route){
        MostreadScreen()
    }
}

@Composable
fun MostreadScreen() {
    Button(onClick = ) {

    }
    Text(text = "Most read screen")
}