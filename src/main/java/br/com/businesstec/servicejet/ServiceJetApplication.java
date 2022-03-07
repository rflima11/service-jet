package br.com.businesstec.servicejet;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.datasources.jet.http.JetAuth;
import br.com.businesstec.servicejet.datasources.jet.http.JetProduto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class ServiceJetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceJetApplication.class, args);
	}

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}


}
