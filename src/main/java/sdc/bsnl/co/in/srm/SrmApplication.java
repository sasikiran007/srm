package sdc.bsnl.co.in.srm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrmApplication.class, args);
		System.setProperty("https.proxyHost", "10.196.222.226");
		System.setProperty("https.proxyPort", "8080");
		System.setProperty("http.proxyHost", "10.196.222.226");
		System.setProperty("http.proxyPort", "8080");
		System.setProperty("http.proxySet", "true");
		System.setProperty("https.proxySet", "true");
		System.setProperty("com.google.api.client.should_use_proxy", "true");
		Logger logger = LoggerFactory.getLogger(SrmApplication.class);
		logger.info("Test log :","Success");
	}

}
