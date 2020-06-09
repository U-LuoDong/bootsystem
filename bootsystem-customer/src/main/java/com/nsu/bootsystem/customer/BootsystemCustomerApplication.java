package com.nsu.bootsystem.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@MapperScan("com.nsu.bootsystem.customer.dao")
@SpringBootApplication
public class BootsystemCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootsystemCustomerApplication.class, args);
    }

}
