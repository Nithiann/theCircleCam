package com.nithiann.thecircle.domain

import com.nithiann.thecircle.domain.models.StreamStart
import com.nithiann.thecircle.infrastructure.remote.dto.StreamStartDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toStreamStart
import org.junit.Test
import org.testng.Assert

internal class StreamStartTest {
    private val streamStart = StreamStart("hello", "123123", "vos@mail.com", "123123")

    @Test
    fun shouldBeSubTypeOfDTO() {
        val streamStartDTO  = StreamStartDTO("hello", "123123", "vos@mail.com", "123123")

        Assert.assertEquals(
            streamStartDTO.toStreamStart(),
            streamStart
        )
    }
}