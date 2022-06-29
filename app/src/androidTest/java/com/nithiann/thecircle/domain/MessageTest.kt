package com.nithiann.thecircle.domain

import com.nithiann.thecircle.domain.models.Message
import com.nithiann.thecircle.infrastructure.remote.dto.MessageDTO
import com.nithiann.thecircle.infrastructure.remote.dto.toMessage
import org.junit.Test
import org.testng.Assert

internal class MessageTest {

    private val message: Message = Message(1, "hello world", "123123", 1, 0)
    @Test
    fun ShouldBeSubTypeOfMessage() {
        val messageDTO = MessageDTO(1, "hello world", "123123", 1, 0)

        Assert.assertEquals(
            messageDTO.toMessage(),
            message
        )
    }
}