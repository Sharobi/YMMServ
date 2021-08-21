package com.retail.ecom.config;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.retail.ecom.utils.Constants.*;

@Component
public class TokenExtractor {
	
	/*@Value("${spring.queries.users-query}")
	private String usersQuery;*/
	
	/*static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "YEWPOS";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	static final String AUTHORITIES_KEY = "Authorities";*/
  
	public Claims extractInfo(String token) {
		Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
    
		JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET);
		Jws claimsJws = jwtParser.parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
		
		Claims claims = (Claims) claimsJws.getBody();
		  
		///System.out.println("claims = " + claims);
		return claims;
	  }
	
	  
	public String getJWT(String username,int id,String authorities,int company_id, int store_id, int country, int state) {//,Authentication authentication
		  //System.out.println("user id = "+ us.findUserByEmail(username));
		  /*final String authorities = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(","));*/
	    String JWT = Jwts.builder()
	        .setSubject(username)
	        .claim(AUTHORITIES_KEY, authorities)
	        .claim(USER_ID, id)
	        .claim(COMPANY_ID, company_id)
	        .claim(STORE_ID,store_id)
	        .claim(COUNTRY, country)
	        .claim(STATE, state)
	        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	        .signWith(SignatureAlgorithm.HS512, SECRET)
	        .compact();
//	    res.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + " " + JWT);
	    return TOKEN_PREFIX + " " + JWT;
	  }
}
