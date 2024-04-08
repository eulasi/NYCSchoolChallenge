package com.clone.schoolsapplicationtwo.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.clone.schoolsapplicationtwo.data.SchoolsItem

@Composable
fun SchoolDetailScreen(school: SchoolsItem) {
    Text(text = school.overviewParagraph ?: "No overview available")
}