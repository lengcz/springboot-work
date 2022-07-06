package com.it2.springboothotdeploy;

import com.it2.springboothotdeploy.controller.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(ServerConfig.class)
public class SpringbootHotdeployApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled","false");
		ConfigurableApplicationContext context=SpringApplication.run(SpringbootHotdeployApplication.class, args);
		ServerConfig config=context.getBean(ServerConfig.class);
		System.out.println(config.toString());
	}

}
