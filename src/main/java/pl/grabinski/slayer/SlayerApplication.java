package pl.grabinski.slayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlayerApplication.class, args);
    }
}
