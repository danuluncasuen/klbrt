package com.dlunc.klbrt.persistence.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DocumentDto {
    String content;
    Long version;
}
