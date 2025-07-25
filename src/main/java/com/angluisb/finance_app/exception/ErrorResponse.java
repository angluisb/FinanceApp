package com.angluisb.finance_app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private int status;
    private String error;
    @Builder.Default
    private String timestamp = LocalDateTime.now().toString();
    private String path;

    public ErrorResponse(String message, int status, String error, String path) {
        this.message = message;
        this.status = status;
        this.error = error;
        this.timestamp =  LocalDateTime.now().toString();
        this.path = path;
    }


}
