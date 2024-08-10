package dev.witor.encurtador.url.service;

import dev.witor.encurtador.url.model.LinkUrl;
import dev.witor.encurtador.url.model.LinkUrlDto;
import dev.witor.encurtador.url.repository.LinkUrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class LinkUrlServiceImpl implements LinkUrlService {
    LinkUrlRepository linkUrlRepository;
    public LinkUrlServiceImpl(LinkUrlRepository linkUrlRepository) {
        this.linkUrlRepository = linkUrlRepository;
    }

    @Override
    public String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(4, 8);
    }

    @Override
    public LinkUrl generateUrl(String url) {

        LinkUrlDto linkDto = new LinkUrlDto(url,generateShortUrl(), LocalDateTime.now());
        LinkUrl linkUrl = new LinkUrl();
        BeanUtils.copyProperties(linkDto, linkUrl);
        return linkUrlRepository.save(linkUrl);
    }

    public LinkUrl findCompleteUrl(String shortUrl){
       return linkUrlRepository.findByShortUrl(shortUrl)
               .orElseThrow(()-> new RuntimeException("Url not found." + shortUrl));
    }
    @Override
    public List<LinkUrlDto> findAll() {
        return linkUrlRepository.findAll().stream()
                .map(link -> new LinkUrlDto(
                        link.getCompleteUrl(),
                        link.getShortUrl(),
                        link.getCreatedAt()))
                .toList();

    }
}
