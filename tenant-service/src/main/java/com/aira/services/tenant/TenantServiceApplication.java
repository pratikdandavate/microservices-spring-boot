package com.aira.services.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TenantServiceApplication {

//    @Value("${spring.kafka.bootstrap-servers}")
//    String bootstrapServers;
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//	Map<String, Object> props = new HashMap<>();
//	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//	props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//	return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, Map<String, ?>> producerFactory() {
//	return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Map<String, ?>> kafkaTemplate() {
//	return new KafkaTemplate<>(producerFactory());
//    }

    @Bean
    public SecurityWebFilterChain springSecurity(ServerHttpSecurity http) {
	http.authorizeExchange().anyExchange().permitAll().and().oauth2Client().and().csrf().disable();

	return http.build();
    }

    @Bean
    public WebClient rest(ReactiveClientRegistrationRepository clients, ServerOAuth2AuthorizedClientRepository authz) {
	ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
		clients, authz);
	oauth2.setDefaultClientRegistrationId("keycloak");

	return WebClient.builder().filter(oauth2).build();
    }

    public static void main(String[] args) {
	SpringApplication.run(TenantServiceApplication.class, args);
    }

}
