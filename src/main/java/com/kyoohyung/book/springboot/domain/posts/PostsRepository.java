package com.kyoohyung.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao역할을 하며, JPA에선 Repository라고 부르며 Interface로 생성한다
 * JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
