package com.retail.ecom.config;

import static java.util.Collections.emptyList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.retail.ecom.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
class TokenAuthenticationService {
		
	  /*static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "YEWPOS";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization";
	  static final String AUTHORITIES_KEY = "Authorities";*/
	  
	  static void addAuthentication(HttpServletResponse res, String username,Authentication authentication) {
		  //System.out.println("user id = "+ us.findUserByEmail(username));
		  ////System.err.println("current time in millis"+new Date(System.currentTimeMillis()));
		  final String authorities = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(","));
	    String JWT = Jwts.builder()
	        .setSubject(username)
	        .claim(Constants.AUTHORITIES_KEY, authorities)
	        .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATIONTIME))
	        .signWith(SignatureAlgorithm.HS512, Constants.SECRET)
	        .compact();
	  ///  System.err.println("++time"+new Date(System.currentTimeMillis() + Constants.EXPIRATIONTIME));
	    res.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + " " + JWT);
	  }

	  static Authentication getAuthentication(HttpServletRequest request) {
	    String token = request.getHeader(Constants.HEADER_STRING);
	    if (token != null) {
	      // parse the token.
	      String user = Jwts.parser()
	          .setSigningKey(Constants.SECRET)
	          .parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
	          .getBody()
	          .getSubject();
	      
	      JwtParser jwtParser = Jwts.parser().setSigningKey(Constants.SECRET);
	      Jws claimsJws = jwtParser.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""));

	      Claims claims = (Claims) claimsJws.getBody();
	      
	     // System.out.println("claims = " + claims);

	      Collection authorities = emptyList();
	      if(claims!=null) {
	      authorities =Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
	                        .map(SimpleGrantedAuthority::new)
	                        .collect(Collectors.toList());
	      }

	      return user != null ?
		  //new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
		  new UsernamePasswordAuthenticationToken(user, null, authorities) :
          null;
	    }
	    return null;
	  }
	  
	  /*public void extractInfo(String token) {
		  Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
      
	      JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET);
	      Jws claimsJws = jwtParser.parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
	
	      Claims claims = (Claims) claimsJws.getBody();
	      
	      System.out.println("claims = " + claims);
	  }*/
	  
	  
	}
