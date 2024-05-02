package com.example.linkshortener.controllers;

import com.example.linkshortener.entities.Link;
import com.example.linkshortener.services.LinkShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linkshortener")
@AllArgsConstructor
public class LinkShortenerController {

    LinkShortenerService service;


    @GetMapping("")
    public String root() {
        return "Hello Wrold!";
    }

    @PostMapping("")
    public Link getShortLink(@RequestBody String link) {
        return service.processLink(link);
    }

}
