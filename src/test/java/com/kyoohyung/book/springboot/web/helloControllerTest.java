package com.kyoohyung.book.springboot.web;

import com.kyoohyung.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) //Springboot test와 JUnit사이의 연결자 역활
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션
public class helloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받습니다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다.
                .andExpect(status().isOk()) // HTTP Header의 Status(200, 404, 500)를 검증, 여기서는  200 OK 인지 검증
                .andExpect(content().string(hello)); // 응답 본분의 내용 검증. 여기서는 "hello"를 리턴 하고 있다.


    }

    @Test
    public void 롬복_기능_테스트(){
        String name ="test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
    @Test
    public void HelloDto가_리턴된다()throws Exception{
        String name = "hello";
        int amount = 100;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))

                .andExpect(jsonPath("$.amount", is(amount)));

    }

}
