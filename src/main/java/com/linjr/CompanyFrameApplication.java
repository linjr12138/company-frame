package com.linjr;


import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@EnableAsync
@EnableConfigurationProperties
@EnableScheduling
public class CompanyFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyFrameApplication.class, args);
    }

}
