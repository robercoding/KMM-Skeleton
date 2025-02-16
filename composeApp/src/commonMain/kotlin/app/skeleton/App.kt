package app.skeleton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.skeleton.ui.theme.AppTheme
import app.skeleton.data.preferences.SettingsPreference
import app.skeleton.di.entryPointModules
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.dsl.KoinAppDeclaration
import skeletonproject.composeapp.generated.resources.Res
import skeletonproject.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(
    startupFinished: () -> Unit = {},
    koinAppDeclaration: KoinAppDeclaration? = null,
) {

    LaunchedEffect(Unit) {
        startupFinished
    }
    KoinApplication(
        application = {
            modules(entryPointModules)
            koinAppDeclaration?.invoke(this)
        },
    ) {
        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = "hey"
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
            val settingsPreference = koinInject<SettingsPreference>()
            val currentSystemTheme = isSystemInDarkTheme()
            LaunchedEffect(Unit) {
                if (settingsPreference.getBooleanData(SettingsPreference.PREFERENCE_THEME_IS_DARK_MODE) == null) {
                    settingsPreference.setBooleanData(SettingsPreference.PREFERENCE_THEME_IS_DARK_MODE, currentSystemTheme)
                }
            }
            val isDarkMode = settingsPreference.getBooleanFlow(SettingsPreference.PREFERENCE_THEME_IS_DARK_MODE).collectAsState(null).value


            if (isDarkMode != null) {
                AppTheme(darkTheme = isDarkMode) {
                    LaunchedEffect(Unit) {
                        startupFinished()
                    }
                    LenthScreen()
                }
            }
        }
    }
}