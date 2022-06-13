package br.com.businesstec.servicejet.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:C:\\config\\jet.properties")
public class FileConfigurationsConfig {
}
