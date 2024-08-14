package com.moa.MoaS3Example.global.exception.message;

import org.springframework.http.HttpStatus;

public interface HttpMessage {
    HttpStatus getStatus();
    String getMessage();
}