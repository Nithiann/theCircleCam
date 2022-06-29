package com.nithiann.thecircle.domain

import com.nithiann.thecircle.domain.models.StreamCheck
import com.nithiann.thecircle.infrastructure.remote.dto.StreamCheckDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toMessage
import com.nithiann.thecircle.infrastructure.remote.dto.toStreamCheck
import org.junit.Test
import org.testng.Assert

internal class StreamCheckTest {

    private val streamCheck = StreamCheck("bas", "123123")

    @Test
    fun shouldBeSubTypeOfDTO() {
        val streamCheckDTO  = StreamCheckDTO("bas", "123123")

        Assert.assertEquals(
            streamCheckDTO.toStreamCheck(),
            streamCheck
        )
    }
}