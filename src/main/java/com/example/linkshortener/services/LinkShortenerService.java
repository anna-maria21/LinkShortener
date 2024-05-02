package com.example.linkshortener.services;

import com.example.linkshortener.DTOs.ShortedLink;
import com.example.linkshortener.entities.Link;
import com.example.linkshortener.exceptions.HashingUrlException;
import com.example.linkshortener.repositories.LinkShortenerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkShortenerService {
    LinkShortenerRepository repository;

    HttpServletRequest request;

    public ShortedLink processLink(String fullLink) {
        Link linkObject = new Link();
        Optional<Link> existsInDb = repository.findByFullLink(fullLink);
        if (existsInDb.isPresent()) {
            return new ShortedLink(request.getRequestURL().toString() + "/" + existsInDb.get().getEncodedLink());
        }
        linkObject.setFullLink(fullLink);
        String encodedLink = hashFullLink(fullLink);
        if (encodedLink != null) {
            linkObject.setEncodedLink(encodedLink);
            repository.save(linkObject);
            return new ShortedLink(request.getRequestURL().toString() + "/" + encodedLink);
        } else {
            throw new HashingUrlException();
        }

    }

    private boolean validateLink(String fullLink) {
        try {
            URL url = new URI(fullLink).toURL();
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            return fullLink.matches("^http(s)?://.*$") && responseCode == HttpURLConnection.HTTP_OK;
        } catch (URISyntaxException | IOException | IllegalArgumentException ignored) {
        }
        return false;
    }

    private String hashFullLink(String fullLink) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(fullLink.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes).replaceAll("/", "%2");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
        }
        return null;
    }

    public String redirectUser(String encodedLink) {
        return repository.findByEncodedLink(encodedLink).getFullLink();
    }
}
