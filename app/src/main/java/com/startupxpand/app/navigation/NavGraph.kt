package com.startupxpand.app.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.startupxpand.app.ui.screens.*
import com.startupxpand.app.ui.theme.*

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "خانه", Icons.Rounded.Home)
    data object Ideas : Screen("ideas", "ایده‌ها", Icons.Rounded.Lightbulb)
    data object Countries : Screen("countries", "کشورها", Icons.Rounded.Public)
    data object Stories : Screen("stories", "روایت‌ها", Icons.Rounded.AutoStories)
    data object Faq : Screen("faq", "سوالات", Icons.Rounded.HelpOutline)
    data object Assessment : Screen("assessment", "ارزیابی", Icons.Rounded.Assignment)
}

val bottomItems = listOf(
    Screen.Home,
    Screen.Ideas,
    Screen.Countries,
    Screen.Stories,
    Screen.Faq
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route in bottomItems.map { it.route }

    Scaffold(
        containerColor = Navy950,
        bottomBar = {
            if (showBottomBar) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Navy950)
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(CardDark)
                            .padding(vertical = 6.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        bottomItems.forEach { screen ->
                            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        screen.icon,
                                        contentDescription = screen.label,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        screen.label,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Cyan400,
                                    selectedTextColor = Cyan400,
                                    unselectedIconColor = Slate500,
                                    unselectedTextColor = Slate500,
                                    indicatorColor = Cyan500.copy(alpha = 0.12f)
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding),
            enterTransition = { fadeIn(tween(220)) + slideInHorizontally(tween(220)) { it / 8 } },
            exitTransition = { fadeOut(tween(180)) },
            popEnterTransition = { fadeIn(tween(220)) },
            popExitTransition = { fadeOut(tween(180)) + slideOutHorizontally(tween(180)) { it / 8 } }
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateIdeas = { navController.navigate(Screen.Ideas.route) },
                    onNavigateStories = { navController.navigate(Screen.Stories.route) },
                    onNavigateAssessment = { navController.navigate(Screen.Assessment.route) }
                )
            }
            composable(Screen.Ideas.route) {
                IdeasScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Countries.route) { CountriesScreen() }
            composable(Screen.Stories.route) { StoriesScreen() }
            composable(Screen.Faq.route) { FaqScreen() }
            composable(Screen.Assessment.route) { AssessmentScreen() }
        }
    }
}
