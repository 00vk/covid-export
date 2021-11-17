package ru.spb.reshenie.covid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.sql.DataSource;

@SpringBootApplication
public class CovidApplication extends SpringBootServletInitializer {

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
	}

}
