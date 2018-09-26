package springbook2.learningtest.ioc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springbook2.learningtest.ioc.bean.AnnotatedHello;

@Configuration
public class AnnotatedHelloConfig {
	
	@Bean
	public AnnotatedHello annotatedHello() {
		return new AnnotatedHello();
	}
	
}
