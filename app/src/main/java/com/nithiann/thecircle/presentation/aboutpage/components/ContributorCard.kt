package com.nithiann.thecircle.presentation.aboutpage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.nithiann.thecircle.domain.models.Contributor
import androidx.compose.ui.Modifier

@Composable
fun ContributorCard(contributor: Contributor) {
    Card(
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(15.dp),

    ) {

        Row(
            modifier = androidx.compose.ui.Modifier
                .width(360.dp)
                .padding(20.dp)

        ) {
            Text(contributor.firstName)
            Text(text = contributor.lastName)
        }
    }
}