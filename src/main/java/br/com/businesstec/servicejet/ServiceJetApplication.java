package br.com.businesstec.servicejet;

import br.com.businesstec.servicejet.client.AuthClienteJet;
import br.com.businesstec.servicejet.client.ClienteJet;
import br.com.businesstec.servicejet.config.JetProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ServiceJetApplication {

	@Autowired
	private JetProperties jetProperties;
	@Autowired
	private AuthClienteJet authClienteJet;
	@Autowired
	private ClienteJet clienteJet;

	public static void main(String[] args) {
		SpringApplication.run(ServiceJetApplication.class, args);
	}

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	CommandLineRunner teste(JetProperties jetProperties) {
		return args -> {
			var r = authClienteJet.auth(jetProperties.getPedido()).getBody().getAccessToken();
			var b = clienteJet.getClientes("Bearer ".concat(r));
			System.out.println(b.getBody());
		};
	}


}
