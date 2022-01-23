package org.unibl.etf.virtualmuseumapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VirtualMuseumApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualMuseumApiApplication.class, args);
    }
}
