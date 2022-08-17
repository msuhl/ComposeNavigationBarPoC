package dk.isten.andro.composeTabbar.ui.theme.navigation.destinations

import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.isten.andro.composeTabbar.ui.theme.navigation.NavDestination
import dk.isten.andro.composeTabbar.ui.theme.navigation.uri

object ArticleDestination : NavDestination {
    const val articleId = "article_id"
    override val route = "article_route/$articleId"
    override val destination = "article_destination"
    val deepLink = "$uri/article"

    fun createNavigationRoute(articleId: String): String {
        val articleIdArg = Uri.encode(articleId)
        return "article_route/$articleIdArg"
    }

    fun fromNavArgs(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(articleId)!!
        return Uri.decode(encodedId)
    }

    fun NavGraphBuilder.articleGraph(
        onbackClick: () -> Unit
    ) {
        composable(
            route = ArticleDestination.route,
            arguments = listOf(
                navArgument(ArticleDestination.articleId) { type = NavType.StringType }
            )
        ){
            ArticleRoute(onbackClick)
        }
    }
}

@Composable
private fun ArticleRoute(
    onbackClick: () -> Unit,
    viewModel: ArticleViewModel = hiltViewModel()
) {

    Text(text ="Hej Svend" )
}