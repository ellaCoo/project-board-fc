package com.fc.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProjectBoardFcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBoardFcApplication.class, args);
    }

}
