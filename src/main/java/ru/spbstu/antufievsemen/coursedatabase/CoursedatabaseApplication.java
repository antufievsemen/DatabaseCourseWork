package ru.spbstu.antufievsemen.coursedatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CoursedatabaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoursedatabaseApplication.class, args);
  }

  @Bean
  public static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
