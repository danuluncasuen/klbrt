package com.dlunc.klbrt.persistence.model;

import lombok.Data;

@Data
public class TextChangeMessage {
    private String content;
    private long version;
}
