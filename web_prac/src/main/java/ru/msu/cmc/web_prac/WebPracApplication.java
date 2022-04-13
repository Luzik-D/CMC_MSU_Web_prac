package ru.msu.cmc.web_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebPracApplication {

	public static void main(String[] args) {
		System.out.println("hello world");
		SpringApplication.run(WebPracApplication.class, args);

		//Films brother = FilmsDAO.getById(1);
		//System.out.println(brother.toString());
		System.out.println("Hello world!");
	}

}
