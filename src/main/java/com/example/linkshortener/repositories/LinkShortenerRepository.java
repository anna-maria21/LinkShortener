package com.example.linkshortener.repositories;

import com.example.linkshortener.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkShortenerRepository extends JpaRepository<Link, Long> {
}
