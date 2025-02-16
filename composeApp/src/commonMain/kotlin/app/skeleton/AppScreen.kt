package app.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.skeleton.navigation.Home
import app.skeleton.navigation.MainNavigation
import app.skeleton.navigation.Settings
import app.skeleton.navigation.bottom.BottomNavigationItem
import app.skeleton.navigation.bottom.BottomScreen
import app.skeleton.ui.screens.home.HomeScreen
import app.skeleton.ui.screens.settings.SettingsScreen
import app.skeleton.utils.openLanguageSettings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        screen = BottomScreen.Home,
        icon = Icons.Default.Home,
        screenNavigation = Home,
        // label =  Res.string.home_tab,
    ),
    BottomNavigationItem(
        screen = BottomScreen.Settings,
        icon = Icons.Default.Settings,
        screenNavigation = Settings,
        // label =  Res.string.settings_tab,
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LenthScreen(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route

    val backgroundColor by rememberUpdatedState(MaterialTheme.colorScheme.background)
    val onSurfaceColor by rememberUpdatedState(MaterialTheme.colorScheme.onSurface)
    val selectedBottomNavigationScreenItem by remember { mutableStateOf(BottomScreen.Home) }

    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        color = backgroundColor,
    ) {
        Scaffold(
            modifier = Modifier.statusBarsPadding(),
            topBar = {},
            bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colorScheme.surface,

                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    bottomNavigationItems.forEach { bottomNavigationItem ->
                        BottomNavigationItem(
                            modifier = Modifier.navigationBarsPadding(),
                            icon = {
                                Icon(
                                    imageVector = bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.icon.name,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                )
                            },
                            label = null,
                            // label = { bottomNavigationItem.label?.let { Text(text = stringResource(it), style = MaterialTheme.typography.labelSmall) } },
                            selected = selectedBottomNavigationScreenItem == bottomNavigationItem.screen,
                            onClick = {
                                if (currentDestination?.route == bottomNavigationItem.screen.name) {
                                    return@BottomNavigationItem
                                }

                                if (bottomNavigationItem.screen.name != BottomScreen.Home.name) {
                                    navController.navigate(bottomNavigationItem.screenNavigation)
                                } else {
                                    navController.popBackStack<Home>(inclusive = false)
                                }
                            },
                        )

                    }
                }
            },
            backgroundColor = backgroundColor,
        ) { paddingValues ->

            Column {

                NavHost(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    navController = navController,
                    startDestination = Home,
                    builder = {
                        composable<Home>() {
                            HomeScreen(
                                homeViewModel = koinViewModel(),
                            )
                        }

                        composable<Settings>() {
                            // Add the Settings screen content here
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text("Settings Content", color = MaterialTheme.colorScheme.onSurface)
                            }

                            SettingsScreen(
                                modifier = Modifier.fillMaxSize(),
                                onClickChangeLanguage = { openLanguageSettings() },
                                settingsViewModel = koinViewModel(),
                            )
                        }
                    },
                )
            }
        }
    }
}

