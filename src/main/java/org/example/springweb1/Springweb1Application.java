package org.example.springweb1;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Springweb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springweb1Application.class, args);
    }


    @Bean
    Hibernate5JakartaModule hibernate5Module(){
        return new Hibernate5JakartaModule();
    }
}
