package com.pjoias.api.config;

public class SecurityConstants {
	static final String SECRET = "PjoiasJwtToken";
	static final String TOKEN_PREFIX = "Bearer ";
	static final String HEADER_STRING = "Authorization";
	static final String SIGN_UP_URL = "/users/sign-up";
	static final long  EXPIRATION_TIME = 86400000L;
}
