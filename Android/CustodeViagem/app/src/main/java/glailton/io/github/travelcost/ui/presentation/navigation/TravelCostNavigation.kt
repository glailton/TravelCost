package glailton.io.github.travelcost.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import glailton.io.github.travelcost.ui.presentation.screens.home.HomeScreen
import glailton.io.github.travelcost.ui.presentation.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

sealed class Routes(val routes: String) {
    object HomeScreenRoute : Routes("home-screen")
}

@Composable
fun TravelCostNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController,
    startDestination = Routes.HomeScreenRoute.routes
    ) {
        navHomeScreen(navController)
    }
}

fun NavGraphBuilder.navHomeScreen(navController: NavHostController) {
    composable(route = Routes.HomeScreenRoute.routes) {
        val viewModel: HomeViewModel = koinViewModel()
        HomeScreen(viewModel)
    }
}
