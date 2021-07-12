package com.primesolutions.shorturlgenerator.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primesolutions.shorturlgenerator.entity.Url;
import com.primesolutions.shorturlgenerator.repository.UrlRepository;

@Service
public class ShortUrlCacheServiceDtoImpl implements ShortUrlCacheServiceDto {
	
	@Autowired 
	private UrlRepository urlRepo;
	
	@Override
	public Optional<Url> getUrlEntityById(Long id) {
		Optional<Url> url = urlRepo.getUrlById(id);
		return url;
	}
	
	@Override
	public Url saveUrl(Url url) {
		url = urlRepo.save(url);
		return url;
	}
	
	@Override
	public Optional<Url> getUrlEntityByShortUrl(String shortUrl) {
		return urlRepo.getUrlByShortUrl(shortUrl);
	}

	@Override
	public List<Url> getAllUrlsList() {
		return urlRepo.findAll();
	}

	@Override
	public Long deleteLongUrl(Long id) {
		urlRepo.deleteById(id);
		return id;
	}
}
