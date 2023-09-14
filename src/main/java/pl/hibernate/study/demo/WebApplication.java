package pl.hibernate.study.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication/*(exclude = { SecurityAutoConfiguration.class })*/
public class WebApplication {
    public static void main(String[] args) throws Throwable{
        SpringApplication.run(WebApplication.class, args);
    }
}
