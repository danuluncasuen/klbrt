package com.dlunc.klbrt.service;

import com.dlunc.klbrt.exception.DocumentNotFoundException;
import com.dlunc.klbrt.persistence.DocumentRepo;
import com.dlunc.klbrt.persistence.model.Document;
import com.dlunc.klbrt.persistence.model.TextChangeMessage;
import com.dlunc.klbrt.persistence.model.dto.DocumentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepo documentRepo;

    public DocumentDto getDocument(Long id) {
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found: " + id));
        return DocumentDto.builder()
                .content(document.getContent())
                .version(document.getVersion())
                .build();
    }

    public Long createDocument(String title) {
        Document document = Document.builder().title(title).content("Your text").build();
        return documentRepo.saveAndFlush(document).getId();
    }

    @Transactional
    public TextChangeMessage updateDocument(Long docId, TextChangeMessage message) {
        Document document = documentRepo.findById(docId)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found: " + docId));

        document.setContent(message.getContent());

        return TextChangeMessage.builder()
                .content(document.getContent())
                .version(document.getVersion())
                .build();
    }

}
