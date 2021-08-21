package com.retail.ecom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/test").setViewName("test2");
   }
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
   	if (!registry.hasMappingForPattern("/webjars/**")) {
   		registry.addResourceHandler("/webjars/**").addResourceLocations(
   				"classpath:/META-INF/resources/webjars/");
   	}
   	if (!registry.hasMappingForPattern("/**")) {
   		registry.addResourceHandler("/**").addResourceLocations(
   				CLASSPATH_RESOURCE_LOCATIONS);
   	}
   }
   /*@Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       if (!registry.hasMappingForPattern("/css/**")) {
           registry.addResourceHandler("/css/**").addResourceLocations(
                   "/resources/static/css/");
       }
       if (!registry.hasMappingForPattern("/images/**")) {
           registry.addResourceHandler("/images/**").addResourceLocations(
                   "/resources/images/");
       }
       if (!registry.hasMappingForPattern("/fonts/**")) {
           registry.addResourceHandler("/fonts/**").addResourceLocations(
                   "/resources/fonts/");
       }
       if (!registry.hasMappingForPattern("/js/**")) {
           registry.addResourceHandler("/js/**").addResourceLocations(
                   "/resources/js/");
       }
   }*/
}
