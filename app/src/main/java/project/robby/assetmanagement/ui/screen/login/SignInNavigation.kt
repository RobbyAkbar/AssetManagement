package project.robby.assetmanagement.ui.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import project.robby.assetmanagement.navigation.Destination

object SignInDestination : Destination {
    override val routeName: String = "SignInDestination"
}

fun NavController.navigateToSignIn(
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.navigate(SignInDestination.routeName, navOptions(builder))
}

fun NavGraphBuilder.signInScreen() {
    composable(SignInDestination.routeName) {
        SignInScreen()
    }
}