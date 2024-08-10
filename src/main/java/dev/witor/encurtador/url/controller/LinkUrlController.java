package dev.witor.encurtador.url.controller;

import dev.witor.encurtador.url.model.LinkUrl;
import dev.witor.encurtador.url.model.LinkUrlDto;
import dev.witor.encurtador.url.service.LinkUrlServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shorturl")
public class LinkUrlController {
    LinkUrlServiceImpl linkUrlService;
    public LinkUrlController(LinkUrlServiceImpl linkUrlService) {
        this.linkUrlService = linkUrlService;
    }
    @PostMapping
    ResponseEntity<LinkUrlDto> createLinkUrl(@RequestBody Map<String, String> url, HttpServletRequest request) {
        String completeUrl = url.get("completeUrl");
        LinkUrl link = linkUrlService.generateUrl(completeUrl);
        String linkRedir = request.getRequestURL().toString().concat("/" + link.getShortUrl());
        LinkUrlDto linkDto = new LinkUrlDto(
                link.getCompleteUrl(),
                linkRedir,
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(linkDto);

    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirToCompleteLink(@PathVariable String shortUrl) {
        LinkUrl completeUrl = linkUrlService.findCompleteUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(completeUrl.getCompleteUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
    @GetMapping
    ResponseEntity<List<LinkUrlDto>> findAllLinks() {
        return ResponseEntity.ok(linkUrlService.findAll());
    }

}
