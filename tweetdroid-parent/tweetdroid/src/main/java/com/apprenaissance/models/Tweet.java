package com.apprenaissance.models;

public class Tweet {
	private String imageUrl;
	private String body;
	
	public Tweet(String body, String imageUrl) {
		this.imageUrl = imageUrl;
		this.body = body;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
