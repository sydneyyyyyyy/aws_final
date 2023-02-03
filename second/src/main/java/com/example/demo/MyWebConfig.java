package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //설정파일
public class MyWebConfig implements WebMvcConfigurer {
	
	//linux
	static final public String savePath = "usr/upload";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/upload/");
		
		//배포 위한 path 수정
		registry.addResourceHandler("/upload/**").addResourceLocations("file:/usr/upload");
		//registry.addResourceHandler("mapping url 이름").addResourceLocations("경로");
		
	}

}
