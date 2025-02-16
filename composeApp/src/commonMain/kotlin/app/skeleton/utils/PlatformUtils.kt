package app.skeleton.utils

import com.plusmobileapps.konnectivity.Konnectivity
import kotlinx.coroutines.flow.Flow

val konnectivity = Konnectivity()
fun isConnected(): Boolean = konnectivity.isConnected // Emit the connectivity changes

fun isConnectedFlow(): Flow<Boolean> = konnectivity.isConnectedState // Emit the connectivity changes

expect fun openBrowser(url: String)

expect fun openLanguageSettings()

expect fun copyToClipboard(text: String)

expect fun isFreshInstall(): Boolean