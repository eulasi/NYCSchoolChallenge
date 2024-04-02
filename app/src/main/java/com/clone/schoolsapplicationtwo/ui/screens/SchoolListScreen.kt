package com.clone.schoolsapplicationtwo.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clone.schoolsapplicationtwo.ui.vm.SchoolsViewModel

@Composable
fun SchoolListScreen(viewModel: SchoolsViewModel) {
    val schools by viewModel.schools.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.padding(18.dp)) {
        items(schools) { school ->
            Text(text = "${school.schoolName} - ${school.dbn}")
            Text(text = "Overview: ${school.overviewParagraph ?: "N/A"}")
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}