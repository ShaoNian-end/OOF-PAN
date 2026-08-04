package com.azhi.oofpan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.azhi.oofpan.ui.navigation.MainNavigation
import com.azhi.oofpan.ui.theme.MiuixTheme
import com.azhi.oofpan.ui.theme.ProvideThemeState
import com.azhi.oofpan.ui.theme.ThemeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeState = remember { ThemeState() }

            MiuixTheme(darkTheme = themeState.isDarkMode) {
                ProvideThemeState(themeState = themeState) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MainNavigation()
                    }
                }
            }
        }
    }
}