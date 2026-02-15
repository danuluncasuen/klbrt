package com.dlunc.klbrt.api;

import com.dlunc.klbrt.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/document")
public class DocumentApi {

    private final DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<?> create() {
        documentService.createDocument();
        return ResponseEntity.ok().build();
    }

}
