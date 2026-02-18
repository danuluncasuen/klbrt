package com.dlunc.klbrt.api;

import com.dlunc.klbrt.exception.DocumentNotFoundException;
import com.dlunc.klbrt.persistence.model.TextChangeMessage;
import com.dlunc.klbrt.service.DocumentService;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class DocumentWebSocketController {

    @Autowired
    private DocumentService documentService;

    @MessageMapping("/document/{docId}/edit")
    @SendTo("/topic/document/{docId}")
    public TextChangeMessage handleEdit(
            @DestinationVariable Long docId,
            TextChangeMessage message) {

        try {
            return documentService.updateDocument(docId, message);
        } catch (OptimisticLockException e) {
            return TextChangeMessage.builder()
                    .content(null)
                    .version(-1L)
                    .error("Document was modified by another user. Please refresh.")
                    .build();

        } catch (DocumentNotFoundException e) {
            return TextChangeMessage.builder()
                    .content(null)
                    .version(-1L)
                    .error("Document not found")
                    .build();
        }
    }
}
