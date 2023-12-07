package co.kr.gaepan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GaePanApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaePanApplication.class, args);
	}

}
