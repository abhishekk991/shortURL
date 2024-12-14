package com.ak.shortURL.repository;

import com.ak.shortURL.model.ShortLinkModel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ShortLinkRepository extends CrudRepository<ShortLinkModel, Long> {

    Optional<ShortLinkModel> findByLongUrl(String longUrl);

    Optional<ShortLinkModel> findByShortUrl(String shortUrl);
}
