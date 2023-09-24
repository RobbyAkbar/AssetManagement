package project.robby.assetmanagement.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import project.robby.assetmanagement.ui.screen.login.navigateToSignIn
import project.robby.assetmanagement.ui.screen.login.signInScreen
import project.robby.assetmanagement.navigation.popUpToInclusive
import project.robby.assetmanagement.ui.screen.dashboard.DashboardDestination
import project.robby.assetmanagement.ui.screen.dashboard.dashboardScreen
import project.robby.assetmanagement.ui.screen.dashboard.navigateToDashboard
import project.robby.assetmanagement.viewmodel.AuthViewModel

@Composable
fun AssetManagementScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = viewModel()
) {
    val authenticationState by viewModel.isAlreadyLogged.collectAsStateWithLifecycle()

    LaunchedEffect(authenticationState) {
        authenticationState?.let { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigateToDashboard {
                    popUpToInclusive(navController.graph.id)
                }
            } else {
                navController.navigateToSignIn {
                    popUpToInclusive(navController.graph.id)
                }
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DashboardDestination.routeName
    ) {
        signInScreen()
        dashboardScreen()
    }
}