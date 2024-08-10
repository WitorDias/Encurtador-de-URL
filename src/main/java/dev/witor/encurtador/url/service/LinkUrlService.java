package dev.witor.encurtador.url.service;

import dev.witor.encurtador.url.model.LinkUrl;
import dev.witor.encurtador.url.model.LinkUrlDto;

import java.util.List;
public interface LinkUrlService {
    String generateShortUrl();

    LinkUrl generateUrl(String url);

    LinkUrl findCompleteUrl(String shortUrl);

    List<LinkUrlDto> findAll();

}
