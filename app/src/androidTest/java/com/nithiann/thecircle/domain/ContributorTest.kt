package com.nithiann.thecircle.domain

import com.nithiann.thecircle.domain.models.Contributor
import com.nithiann.thecircle.infrastructure.remote.dto.ContributorDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toContributor
import org.junit.Test
import org.testng.Assert

internal class ContributorTest {

    private val contributor = Contributor("Mobile", "Bas", 1, "Voss", 2152912)

    @Test
    fun shouldBeSubTypeOfContributor() {
        val contributorDTO = ContributorDTO("Mobile", "Bas", 1, "Voss", 2152912)

        Assert.assertEquals(
            contributorDTO.toContributor(),
            contributor
        )
    }

}