package com.pink.retail;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pink.retail.mail.ScheduleClass;

@SpringBootApplication
public class RetailAppApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		//SpringApplication.run(RetailAppApplication.class, args);	
		SpringApplicationBuilder builder = new SpringApplicationBuilder(RetailAppApplication.class);
		builder.headless(false).run(args);
	}

	@Autowired
	ScheduleClass scheduleClass;
	
	@Override
	public void run(String... args) throws Exception {
		scheduleClass.scheduleFixedRateWithInitialDelayTask();
		
	}

}
@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("PUT", "DELETE","POST","GET")
            .allowCredentials(false).maxAge(3600);
    }
}