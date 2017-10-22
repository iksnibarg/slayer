package pl.grabinski.slayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class SlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlayerApplication.class, args);
    }
}
