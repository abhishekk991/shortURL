package com.ak.shortURL.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ShortLinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "long_url", nullable = false)
    String longUrl;

    String sha256Value;

    // @Column(name = "short_url", nullable = false)
    String shortUrl;

}
