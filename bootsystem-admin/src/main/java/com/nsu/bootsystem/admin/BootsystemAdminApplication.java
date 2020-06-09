package com.nsu.bootsystem.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.nsu.bootsystem.admin.dao")
@SpringBootApplication
public class BootsystemAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootsystemAdminApplication.class, args);
    }

}
