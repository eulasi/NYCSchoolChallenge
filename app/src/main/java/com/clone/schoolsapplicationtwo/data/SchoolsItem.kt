package com.clone.schoolsapplicationtwo.data

import com.google.gson.annotations.SerializedName

typealias Schools = List<SchoolsItem>
data class SchoolsItem(
    @SerializedName("dbn")
    val dbn: String? = "",
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = "",
    @SerializedName("school_name")
    val schoolName: String? = "",
)