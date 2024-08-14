package com.moa.MoaS3Example.global.exception.exception;

import com.moa.MoaS3Example.global.exception.message.HttpMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BusinessException(HttpMessage response) {
        super(response.getMessage());
        this.status = response.getStatus();
        this.message = response.getMessage();
    }
}