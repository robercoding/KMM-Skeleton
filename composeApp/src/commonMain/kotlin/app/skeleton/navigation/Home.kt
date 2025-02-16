package app.skeleton.navigation

import kotlinx.serialization.Serializable


// Define a home route that doesn't take any arguments
@Serializable
data object Home: MainNavigation()