package com.example.linkshortener.DTOs;

import com.example.linkshortener.annotations.UrlConstraint;

public record LinkDTO(
        Long id,
        @UrlConstraint
        String fullLink,
        String encodedLink
) {
}
