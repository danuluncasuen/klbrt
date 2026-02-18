package com.dlunc.klbrt.api;

import com.dlunc.klbrt.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/document")
public class DocumentApi {

    private final DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody String title) {
        Long id = documentService.createDocument(title);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(documentService.getDocument(id));
    }

}
