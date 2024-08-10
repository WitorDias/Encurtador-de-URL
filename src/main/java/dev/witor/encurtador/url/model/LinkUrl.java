package dev.witor.encurtador.url.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LinkUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String completeUrl;
    private String shortUrl;
    private LocalDateTime createdAt;

}
