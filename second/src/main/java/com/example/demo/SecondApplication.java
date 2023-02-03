package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //내가 설정한 클래스 파일,com.example.demo 에 있는 것만 scan 할 경우
//설정파일 + 스프링 시작 권한(tomcat 시작)도 가짐

@ComponentScan
@ComponentScan(basePackages = {"upload","board.spring.mybatis"})
@ComponentScan(basePackages = "spring.mybatis")
@ComponentScan(basePackages = "websocket")
@ComponentScan(basePackages="dbsecure")

@MapperScan(basePackages = {"spring.mybatis","board.spring.mybatis"})
public class SecondApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecondApplication.class, args);
	}

}
