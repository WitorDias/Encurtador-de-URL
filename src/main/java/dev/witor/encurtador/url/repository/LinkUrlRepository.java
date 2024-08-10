package dev.witor.encurtador.url.repository;

import dev.witor.encurtador.url.model.LinkUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkUrlRepository extends JpaRepository<LinkUrl, Long> {
    Optional<LinkUrl>findByShortUrl(String url);
    Optional<LinkUrl> findByCompleteUrl(String url);
    Optional<LinkUrl>findById(Long id);

}
