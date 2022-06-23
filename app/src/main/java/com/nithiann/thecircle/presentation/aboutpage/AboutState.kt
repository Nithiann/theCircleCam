package com.nithiann.thecircle.presentation.aboutpage

import com.nithiann.thecircle.domain.models.Contributor

data class AboutState (
    val isLoading: Boolean = false,
    val contributors: List<Contributor>? = null,
    val error: String = ""
)