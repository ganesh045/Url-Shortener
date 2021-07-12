package com.primesolutions.shorturlgenerator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primesolutions.shorturlgenerator.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

	public Optional<Url> getUrlById(Long id);
	
	public Optional<Url> getUrlByShortUrl(String shortUrl);
	
}
