package com.kyoohyung.book.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  //JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정, 보통 테스트간의 H2 가비지 데이터의 영향을 없애기 위해 처리함.
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //postsRepository.save : insert/update 쿼리를 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("author@gggmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();// 모든 데이터 조회

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {

        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        List<Posts> postList = postsRepository.findAll();

        Posts posts = postList.get(0);

        System.out.println(">>>>>> Created Date = " + posts.getCreatedDate() + ", Modified Date = " + posts.getModifiedDate());


        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
