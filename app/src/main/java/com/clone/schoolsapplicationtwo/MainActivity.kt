package com.clone.schoolsapplicationtwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.clone.schoolsapplicationtwo.ui.screens.SchoolListScreen
import com.clone.schoolsapplicationtwo.ui.vm.SchoolsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SchoolsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolListScreen(viewModel = viewModel)
        }
    }
}