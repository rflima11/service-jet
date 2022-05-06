package br.com.businesstec.servicejet.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.businesstec.model")
@EntityScan(basePackages = "br.com.businesstec.model")
public class ScanPackagesConfig {
}