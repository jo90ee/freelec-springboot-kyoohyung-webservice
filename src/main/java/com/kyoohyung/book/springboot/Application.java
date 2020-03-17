package com.kyoohyung.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화 // DB에 생성, 수정 시간을 기록 하는 기능을 말함. ==> 인증처리를 적용하고 나서 테스트를 위해 config패키지에 JpaConfig를 생성해서 EnableJpaAuditing 를 추가함.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
