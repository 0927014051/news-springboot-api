package com.javaspringboot.news;

import com.javaspringboot.news.entities.Role;
import com.javaspringboot.news.entities.User;
import com.javaspringboot.news.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NewsApplication  {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}
}
