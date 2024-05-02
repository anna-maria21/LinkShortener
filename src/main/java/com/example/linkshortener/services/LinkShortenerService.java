package com.example.linkshortener.services;

import com.example.linkshortener.entities.Link;
import com.example.linkshortener.exceptions.HashingUrlException;
import com.example.linkshortener.repositories.LinkShortenerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@AllArgsConstructor
public class LinkShortenerService {
    LinkShortenerRepository repository;

    HttpServletRequest request;

    public Link processLink(String fullLink) {
        Link linkObject = new Link();
        linkObject.setFullLink(fullLink);
        String shortedLink = hashFullLink(fullLink);
        if (shortedLink != null) {
            linkObject.setShortedLink(request.getRequestURL().toString() + shortedLink);
            return repository.save(linkObject);
        } else throw new HashingUrlException();

    }

    private String hashFullLink(String fullLink) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(fullLink.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
        }
        return null;
    }
}
