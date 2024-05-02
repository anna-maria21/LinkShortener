package com.example.linkshortener.entities;

import com.example.linkshortener.annotations.UrlConstraint;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @UrlConstraint
    @Column(unique = true)
    String fullLink;

    @Column(unique = true)
    String encodedLink;
}
