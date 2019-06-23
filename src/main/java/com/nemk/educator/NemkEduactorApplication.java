package com.nemk.educator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Path;
import java.nio.file.Paths;

//https://www.youtube.com/watch?v=lIgFe20dYq4
//    https://www.youtube.com/watch?v=hZl-JnoS_ro
//https://stackoverflow.com/questions/49608146/spring-boot-html-5-video-streaming

@SpringBootApplication
public class NemkEduactorApplication {
    public static void main(String[] args) {
        SpringApplication.run(NemkEduactorApplication.class, args);
    }

//    @Bean
//    Path path() {
//        return Paths.get(System.getProperty("java.io.tmpdir"));
//    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
