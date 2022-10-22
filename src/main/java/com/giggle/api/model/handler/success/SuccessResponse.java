package com.giggle.api.model.handler.success;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class SuccessResponse {
    private final String Message;

    public SuccessResponse(String Message) {
        this.Message = Message;
    }

    @JsonProperty
    public String getMessage() {
        return Message;
    }
}
