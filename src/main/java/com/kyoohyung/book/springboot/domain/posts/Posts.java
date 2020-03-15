package com.kyoohyung.book.springboot.domain.posts;

import com.kyoohyung.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *  Entity 클래스에서는 절대로!! Setter를 만들지 않는다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼수 있는 메소드를 추가해야 한다.
 */
@Getter // 클래스 내 모든 필드의 Getter 자동 생
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity //JPA 어노테이션 필수!!, 테이블과 링크퇼 클래스임을 알려준다.
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙
    private Long id;

    @Column(length = 500, nullable = false) // @column은 추가 안해도 되는데 기본설정값 말고 다르게 사용하려면 지정하고 쓴다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //빌더 패턴 클래스 생성, 생성자에 포함된 필드만 빌더에 포함됨.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
