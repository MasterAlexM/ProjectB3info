package fr.b3info.projecteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProjectEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEurekaApplication.class, args);
	}

}
