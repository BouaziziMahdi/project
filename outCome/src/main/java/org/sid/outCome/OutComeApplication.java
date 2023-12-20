package org.sid.outCome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class OutComeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutComeApplication.class, args);
	}

}
