package project.robby.assetmanagement.ui.screen.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import project.robby.assetmanagement.navigation.Destination

object DashboardDestination : Destination {
    override val routeName: String = "dashboard"
}

fun NavController.navigateToDashboard(
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.navigate(DashboardDestination.routeName, navOptions(builder))
}

fun NavGraphBuilder.dashboardScreen() {
    composable(route = DashboardDestination.routeName) {
        DashboardScreen()
    }
}