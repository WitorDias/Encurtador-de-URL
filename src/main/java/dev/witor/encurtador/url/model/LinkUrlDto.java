package dev.witor.encurtador.url.model;

import java.time.LocalDateTime;
public record LinkUrlDto(String completeUrl, String shortUrl, LocalDateTime createdAt) {
}
