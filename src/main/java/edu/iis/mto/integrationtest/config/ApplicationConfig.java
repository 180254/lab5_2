package edu.iis.mto.integrationtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("edu.iis.mto.integrationtest.repository")
@Import(value = { PersistenceConfig.class })
public class ApplicationConfig {

}
