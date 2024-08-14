package com.moa.MoaS3Example.global.exception.response;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ErrorResponse {
    private String httpMethod;
    private String path;
    private String message;
    private LocalDateTime timestamp;
    private Exception error;

    public static ErrorResponse of(final String message, final HttpServletRequest request) {
        return ErrorResponse.builder()
                .httpMethod(request.getMethod())
                .path(request.getRequestURI())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(final String message, final HttpServletRequest request, Exception error) {
        return ErrorResponse.builder()
                .httpMethod(request.getMethod())
                .path(request.getRequestURI())
                .message(message)
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
