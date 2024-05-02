package com.example.linkshortener.controllers;

import com.example.linkshortener.DTOs.ShortedLink;
import com.example.linkshortener.entities.Link;
import com.example.linkshortener.services.LinkShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/linkshortener")
@AllArgsConstructor
public class LinkShortenerController {

    LinkShortenerService service;


    @GetMapping("")
    public String root() {
        return "qwerty";
    }

    @PostMapping("")
    public ShortedLink getShortLink(@Valid @RequestBody Link link) {
        return service.processLink(link.getFullLink());
    }

    @GetMapping("/{encodedLink}")
    public void redirect(@PathVariable String encodedLink, HttpServletResponse response) throws IOException {
        response.sendRedirect(service.redirectUser(encodedLink));
    }

}
