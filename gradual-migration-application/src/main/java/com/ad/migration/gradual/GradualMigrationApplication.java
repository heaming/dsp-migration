package com.ad.migration.gradual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ad.migration")
public class GradualMigrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradualMigrationApplication.class, args);
    }
}