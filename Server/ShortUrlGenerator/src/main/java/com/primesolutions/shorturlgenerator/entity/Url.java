package com.primesolutions.shorturlgenerator.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="url")
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String shortUrl;
	
	@Column(nullable = false)
	private String longUrl;
	
	private ZonedDateTime createdDate;
	
	private ZonedDateTime expiredDate;
	
	public Url() {}
	
	public Url(Long id, String shortUrl, String longUrl, ZonedDateTime createdDate, ZonedDateTime expiredDate) {
		this.id = id;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.createdDate = createdDate;
		this.expiredDate = expiredDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ZonedDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(ZonedDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	@Override
	public boolean equals(Object url) {
		if(!(url instanceof Url)){
			return false;
		}
		return ((Url) url).getId().equals(this.id) && ((Url) url).getLongUrl().equals(this.longUrl);
	}
	
	@Override
	public String toString() {
		return "Url [id=" + id +", shortUrl=" + shortUrl + ", longUrl=" + longUrl + ", CreateDate=" + createdDate +
				", expiredDate=" + expiredDate + " ]";
	}
}
