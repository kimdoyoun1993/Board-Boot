package org.zerock.b2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 활성화
public class B2Application {

    public static void main(String[] args) {
        SpringApplication.run(B2Application.class, args);
    }

}
