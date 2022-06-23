package com.nithiann.thecircle.infrastructure.remote.dto

import com.nithiann.thecircle.domain.models.Contributor

data class ContributorDTO(
    val devTeam: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val studentNumber: Int
)

fun ContributorDTO.toContributor() : Contributor {
    return Contributor(
        devTeam = devTeam,
        firstName = firstName,
        id = id,
        lastName = lastName,
        studentNumber = studentNumber
    )
}
