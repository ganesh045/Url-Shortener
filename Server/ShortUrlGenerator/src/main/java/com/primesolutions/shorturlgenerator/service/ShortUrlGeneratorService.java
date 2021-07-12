package com.primesolutions.shorturlgenerator.service;

import java.util.List;

import com.primesolutions.shorturlgenerator.entity.Url;

public interface ShortUrlGeneratorService {

	/**
	 * This method will take the Url and will return saved entity details
	 * @param url will holds the Url object
	 * @return saved Url Entity
	 */
	Url saveLongUrl(Url url);
	
	/**
	 * This method will make request to get the Url based on ShortUrl
	 * @param shortUrl will holds the short url value
	 * @return Url which matched with given shortUrl
	 * @throws Exception
	 */
	Url getLongUrl(String shortUrl) throws Exception;
	
	/**
	 * As name suggest, will return all Url objects data
	 * @return List of Urls saved till now
	 */
	List<Url> getAllUrls();
	
	/**
	 * As name suggest, will delete the Url which matched with given id
	 * @param id holds value
	 * @return id of the deleted Url
	 */
	Long deleteUrl(Long id);
}
