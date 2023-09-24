package project.robby.assetmanagement.navigation

import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpToInclusive(destination: Destination) {
    popUpTo(destination.routeName) {
        inclusive = true
    }
}

fun NavOptionsBuilder.popUpToInclusive(navGraphId: Int) {
    popUpTo(navGraphId) {
        inclusive = true
    }
}