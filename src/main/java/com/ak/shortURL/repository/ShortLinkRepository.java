package com.ak.shortURL.repository;

import com.ak.shortURL.model.ShortLinkModel;
import org.springframework.data.repository.CrudRepository;

public interface ShortLinkRepository extends CrudRepository<ShortLinkModel, Long> {
}
