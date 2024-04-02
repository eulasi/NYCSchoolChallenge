package com.clone.schoolsapplicationtwo.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.clone.schoolsapplicationtwo.vm.SchoolsViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun SchoolListScreen(viewModel: SchoolsViewModel) {
    val schools by viewModel.schools.observeAsState(emptyList())

    LazyColumn {
        items(schools) { school ->
            Text(text = "${school.schoolName} - ${school.dbn}")
            Text(text = "Overview: ${school.overviewParagraph ?: "N/A"}")
        }
    }
}