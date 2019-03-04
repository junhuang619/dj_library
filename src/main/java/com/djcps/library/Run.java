package com.djcps.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.djcps.library.mapper")
@ComponentScan(basePackages="com.djcps.library")
@ServletComponentScan
public class Run {
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
}
