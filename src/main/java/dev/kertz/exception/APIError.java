package dev.kertz.exception;

import java.time.LocalDateTime;
public record APIError(String path, String message, int status, LocalDateTime localDateTime) { }
