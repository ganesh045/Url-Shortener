package com.primesolutions.shorturlgenerator.dto;

import java.util.List;
import java.util.Optional;

import com.primesolutions.shorturlgenerator.entity.Url;

public interface ShortUrlCacheServiceDto {
	
	/**
	 * This method requests to save the Url entity to Database
	 * @param url holds the Url object
	 * @return saved Entity value
	 */
	Url saveUrl(Url url);
	
	/**
	 * This method will get the Url entity based on id
	 * @param id holds unique value
	 * @return Url entity encapsulated with Optional class
	 */
	Optional<Url> getUrlEntityById(Long id);

	/**
	 * This method will request the list of Url entities
	 * @return list of entity objects
	 */
	List<Url> getAllUrlsList();

	/**
	 * This method will make a request to get the Url entity which matches with shortUrl
	 * @param shortUrl holds the string value
	 * @return Url entity encapsulated with Optional class
	 */
	Optional<Url> getUrlEntityByShortUrl(String shortUrl);

	/**
	 * This method will request to delete the Url entity based on matched id
	 * @param id holds long value
	 * @return id of the deleted object
	 */
	Long deleteLongUrl(Long id);

}
