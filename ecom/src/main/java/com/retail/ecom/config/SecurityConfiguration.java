package com.retail.ecom.config;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail.ecom.model.Path;
import com.retail.ecom.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/orders/getPgResponse","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/prescription/**","*.css","*.js");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		List<Path> matchers=userService.findPaths();
		//List<Path> matchers=userService.findPathsByGroup();
		
		List<Path> matchers=userService.findPathsByGroup();
		
		http.csrf().disable()
		.authorizeRequests()
//        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/orders/getPgResponse").permitAll()
		.antMatchers(HttpMethod.GET, "/css/**").permitAll();
		for(Path m : matchers) {
//			System.out.println("Path = "+m.getPath()+" role = "+m.getRole().getRole());
			//System.out.println("Path = "+m.getPath()+" role = "+m.getRolestr());
			//if(m.getRoleId()>0) {
			if(m.getRolestr().contains("All")) {
//				http.authorizeRequests().antMatchers(m.getPath()).hasAnyAuthority(m.getRole().getRole());//working
//				http.authorizeRequests().antMatchers(m.getPath()).hasAnyAuthority("ADMIN","SUB_ADMIN");
				http.authorizeRequests().antMatchers(m.getPath()).permitAll();
			} else {
				//http.authorizeRequests().antMatchers(m.getPath()).permitAll();
				//System.out.println("Path = "+m.getPath()+" roles = "+m.getRolestr());
				http.authorizeRequests().antMatchers(m.getPath()).hasAnyAuthority(m.getRolestr().split(","));
//				http.authorizeRequests().antMatchers(m.getPath()).hasAnyAuthority("USER");
				
			}
		      /*http.authorizeRequests().antMatchers(m.getPath().toString())
		        .access("hasRole('"+m.getRole().toString()+"')")
		        ;*/
		}
		
		http.
		authorizeRequests()
				.anyRequest()
				.authenticated().and()
		        // We filter the api/login requests
		        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(),userService),
		                UsernamePasswordAuthenticationFilter.class)
		        // And filter other requests to check the presence of JWT in header
		        .addFilterBefore(new JWTAuthenticationFilter(),
		                UsernamePasswordAuthenticationFilter.class);
		
	}
	
}