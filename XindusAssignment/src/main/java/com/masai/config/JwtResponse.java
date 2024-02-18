package com.masai.config;

public class JwtResponse {

	private String username;
	private String token;

	public JwtResponse(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
