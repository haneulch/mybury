package com.rsupport.bucketlist.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.rsupport.bucketlist")
@EntityScan("com.rsupport.bucketlist.core.domain")
@EnableJpaRepositories("com.rsupport.bucketlist.core.repository")
@PropertySource(value = { "classpath:application.properties", "file:/DATA/WEB/mybury/config/application.properties"}, ignoreResourceNotFound = true)
@EnableScheduling
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }
}
