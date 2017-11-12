package org.onbrains.springlerning;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
		context.close();
	}

}