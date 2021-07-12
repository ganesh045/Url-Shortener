package com.primesolutions.shorturlgenerator.service;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primesolutions.shorturlgenerator.dto.ShortUrlCacheServiceDto;
import com.primesolutions.shorturlgenerator.entity.Url;

@Service
public class ShortUrlGeneratorServiceImpl implements ShortUrlGeneratorService {

	@Autowired
	private ShortUrlCacheServiceDto dtoService;

	@Autowired
	private ShortUrlBaseConvertionService baseService;

	@Override
	public Url saveLongUrl(Url url) {

		Url cachedUrl = new Url();

		if (url.getId() != null) {
			Optional<Url> optUrl = dtoService.getUrlEntityById(url.getId());
			if(optUrl.isPresent()) {
				cachedUrl = dtoService.saveUrl(url);
			} else {
				throw new EntityNotFoundException("There is no entity with " + url.getId());
			}
		} else {
			url.setCreatedDate(ZonedDateTime.now());
			url.setExpiredDate(ZonedDateTime.now().plus(Period.ofDays(5)));
			cachedUrl = dtoService.saveUrl(url);
		}

		url.setShortUrl(baseService.encode(url.getId()));
		return cachedUrl;
	}
	
	@Override
	public Url getLongUrl(String shortUrl) throws Exception {
		
		Url url = new Url();
		Optional<Url> optUrl = dtoService.getUrlEntityByShortUrl(shortUrl);
		
		if(optUrl.isPresent()) {
			url = optUrl.get();
		} else {			
			Long id = baseService.decode(shortUrl);
			System.out.println("Long id " + id);
			if(id.equals(0l)) {
				throw new Exception();
			}
			Optional<Url> oUrl= dtoService.getUrlEntityById(id);
			if(oUrl.isPresent()) {
				url = oUrl.get();
			} else {
				throw new EntityNotFoundException("There is no entity with " + url.getId());
			}
		}
		return url;
	}

	@Override
	public List<Url> getAllUrls() {
		List<Url> urls = dtoService.getAllUrlsList();
		urls.stream().forEach(m -> m.setShortUrl(baseService.encode(m.getId())));
		return urls;
	}

	@Override
	public Long deleteUrl(Long id) {
		Optional<Url> optUrl= dtoService.getUrlEntityById(id);
		if(optUrl.isPresent()) {
			return dtoService.deleteLongUrl(id);
		} 
		throw new EntityNotFoundException("There is no entity with " + id);
	}

	

}
