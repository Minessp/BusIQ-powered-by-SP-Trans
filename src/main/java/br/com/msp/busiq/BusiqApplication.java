package br.com.msp.busiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusiqApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusiqApplication.class, args);
	}
}
