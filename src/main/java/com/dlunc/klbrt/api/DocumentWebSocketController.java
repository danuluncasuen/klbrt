package com.dlunc.klbrt.api;

import com.dlunc.klbrt.persistence.model.TextChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class DocumentWebSocketController {

    @MessageMapping("/document/{docId}/edit")
    @SendTo("/topic/document/{docId}")
    public TextChangeMessage handleEdit(
        @DestinationVariable String docId,
        TextChangeMessage message
    ) {
        log.info("Received edit for doc {}", docId);
        return message;
    }
}
