package edu.iis.mto.integrationtest.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import edu.iis.mto.integrationtest.utils.ModeUtils;

@Configuration
@ComponentScan("edu.iis.mto.integrationtest.repository")
@Import(value = { PersistenceConfig.class })
public class ApplicationConfig {

	@Bean
	public PropertyPlaceholderConfigurer resolver() {
		PropertyPlaceholderConfigurer resolver = new PropertyPlaceholderConfigurer();
		resolver.setLocation(new ClassPathResource(ModeUtils.getMode().getModeName()
				+ "-persistence.properties"));
		return resolver;
	}
}
