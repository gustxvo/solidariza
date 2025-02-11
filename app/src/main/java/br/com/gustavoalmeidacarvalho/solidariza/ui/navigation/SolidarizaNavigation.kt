package br.com.gustavoalmeidacarvalho.solidariza.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor.CriarDistribuidorScreen
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.registro.CriarRegistroScreen
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.distribuidor.DistribuidorScreen
import br.com.gustavoalmeidacarvalho.solidariza.ui.screens.home.HomeScreen

@Composable
fun SolidarizaNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = SolidarizaDestination.Home.route,
        modifier = modifier,
    ) {
        composable(route = SolidarizaDestination.Home.route) {
            HomeScreen(
                onAddClick = {
                    navController.navigate(SolidarizaDestination.CriarDistribuidor.route)
                },
                onCardClick = { id ->
                    navController.navigate("${SolidarizaDestination.Distribuidor.route}/$id")
                },
            )
        }
        composable(route = SolidarizaDestination.CriarDistribuidor.route) {
            CriarDistribuidorScreen(
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateUp = { navController.navigateUp() },
                onAddClick = { navController.popBackStack() }
            )
        }
        composable(
            route = "${SolidarizaDestination.Distribuidor.route}/{id}",
            arguments = listOf(
                navArgument("id") { NavType.IntType }
            )
        ) {
            DistribuidorScreen(
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateBack = { navController.navigateUp() },
                onAddClick = { distribuidorId, regiao ->
                    navController.navigate(
                        "${SolidarizaDestination.CriarRegistro.route}/$distribuidorId/$regiao"
                    )
                },
            )
        }
        composable(
            route = "${SolidarizaDestination.CriarRegistro.route}/{distribuidor_id}/{regiao}",
            arguments = listOf(
                navArgument("distribuidor_id") { type = NavType.IntType },
                navArgument("regiao") { type = NavType.StringType }
            )
        ) {
            CriarRegistroScreen(
                canNavigateBack = navController.previousBackStackEntry != null,
                onNavigateUp = { navController.navigateUp() },
                onAddClick = { navController.popBackStack() }
            )
        }
    }
}