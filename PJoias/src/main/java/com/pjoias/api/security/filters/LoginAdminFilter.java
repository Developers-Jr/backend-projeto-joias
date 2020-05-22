package com.pjoias.api.security.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
@Order(1)
public class LoginAdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		//Checa intecao de acesso a API, se a intencao nao for acessa-la, prossiga a operacao
		if(!httpRequest.getServletPath().startsWith("/api")) {
			
			chain.doFilter(request, response);
			return;
		}
		
		//Checa se a intencao é logar, se sim, prossiga
		if(httpRequest.getServletPath().startsWith("/api/login")) {
			
			chain.doFilter(request, response);
			return;
		}
		
		Cookie token = WebUtils.getCookie(httpRequest, "token");
		if(token == null) {
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		
		try {
			
			String jwt = token.getValue();
			
			DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256("caiomello"))
				.build()
				.verify(jwt);
			
			Long idUsuarioLogado = decodedJwt.getClaim("idUsuario").asLong();
			httpRequest.setAttribute("idUsuarioLogado", idUsuarioLogado);

			chain.doFilter(httpRequest, httpResponse);
			
		} catch(JWTVerificationException e) {
			httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
			return;
		}
	}
}
