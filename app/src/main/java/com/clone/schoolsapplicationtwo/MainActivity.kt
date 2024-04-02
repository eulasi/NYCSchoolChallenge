package com.clone.schoolsapplicationtwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clone.schoolsapplicationtwo.ui.screens.SchoolListScreen
import com.clone.schoolsapplicationtwo.ui.theme.SchoolsApplicationTwoTheme
import com.clone.schoolsapplicationtwo.vm.SchoolsViewModel

// MainActivity.kt
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SchoolsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolListScreen(viewModel = viewModel)
        }
    }
}