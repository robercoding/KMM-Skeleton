package app.skeleton.navigation.bottom

import androidx.compose.ui.graphics.vector.ImageVector
import app.skeleton.navigation.MainNavigation
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

/**
 * enum values that represent the screens in the app
 */
@Serializable
enum class BottomScreen {
    Home,
    Settings
}

data class BottomNavigationItem(
    val screen: BottomScreen,
    val screenNavigation: MainNavigation,
    val icon: ImageVector,
    val label: StringResource? = null,
)