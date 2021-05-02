package com.mastery.java.task.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "com.mastery.java.task")
public class AppConfiguration {

    @Bean
    @Profile("dev")
    @Primary
    public DataSource getSource() {
        DriverManagerDataSource builder = new DriverManagerDataSource();
        builder.setDriverClassName("org.postgresql.Driver");
        builder.setUrl("jdbc:postgresql://localhost:5432/employeedb");
        builder.setUsername("postgres");
        builder.setPassword("user");
        return builder;
    }

    @Bean
    @Profile("test")
    public DataSource getTestSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("init-base.sql")
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }
}
