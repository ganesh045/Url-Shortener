package com.primesolutions.shorturlgenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primesolutions.shorturlgenerator.entity.Url;
import com.primesolutions.shorturlgenerator.service.ShortUrlGeneratorService;

@RestController
@RequestMapping("url")
public class UrlRestController {

	@Autowired
	private ShortUrlGeneratorService service;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public ResponseEntity<List<Url>> getAllUrls() throws Exception {
		List<Url> urls = service.getAllUrls();
		return new ResponseEntity<List<Url>>(urls, HttpStatus.OK);
	}
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public ResponseEntity<Url> saveLongUrl(@RequestBody Url url) {
		Url savedUrl = service.saveLongUrl(url);
		return new ResponseEntity<Url>(savedUrl, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{shortUrl}", method = RequestMethod.GET)
	public ResponseEntity<Url> getLongUrl(@PathVariable String shortUrl) throws Exception {
		Url url = service.getLongUrl(shortUrl);
		return new ResponseEntity<Url>(url, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteLongUrl(@PathVariable Long id) {
		System.out.println("delte request -- " + id);
		id = service.deleteUrl(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
}
