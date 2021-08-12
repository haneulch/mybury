package com.mybury.bucketlist.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.mybury.bucketlist")
@EntityScan("com.mybury.bucketlist.core.domain")
@EnableJpaRepositories("com.mybury.bucketlist.core.repository")
@PropertySource(value = { "classpath:application.properties", "classpath:config.properties"}, ignoreResourceNotFound = true)
@EnableScheduling
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }
}
