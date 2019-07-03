package com.videorental.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author oleciwoj
 */
@ComponentScan("com.videorental")
@SpringBootApplication
public class VideoRentalApp {

    public static void main(String[] args) {
        SpringApplication.run(VideoRentalApp.class, args);
    }
}
