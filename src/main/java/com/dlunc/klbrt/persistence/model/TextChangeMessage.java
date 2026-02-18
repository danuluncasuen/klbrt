package com.dlunc.klbrt.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextChangeMessage {
    private String content;
    private long version;
    private String error;
}
