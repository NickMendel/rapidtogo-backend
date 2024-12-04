package io.rapidtogo.rapidtogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RapidtogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RapidtogoApplication.class, args);
	}

}
