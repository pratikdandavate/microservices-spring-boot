package com.airamatrix.airadhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableDiscoveryClient
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AiradhiDemoApplication {

    public static void main(String[] args) {
	SpringApplication.run(AiradhiDemoApplication.class, args);
    }

//    @Bean
//    public DataSource getDataSource() {
//	DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//	dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//	dataSourceBuilder.url("jdbc:mysql://172.28.42.132:3306");
//	dataSourceBuilder.username("root");
//	dataSourceBuilder.password("root1234");
//	return dataSourceBuilder.build();
//    }

}
