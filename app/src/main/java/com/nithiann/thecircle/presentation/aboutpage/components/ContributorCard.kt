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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ContributorCard(contributor: Contributor) {
    Card(
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(15.dp),

    ) {

        Row(
            modifier = Modifier
                .width(360.dp)
                .padding(20.dp),
            Arrangement.SpaceBetween

        ) {
            Text(contributor.firstName + " " + contributor.lastName, style = TextStyle(color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer)))
            Text(contributor.devTeam, style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }
}