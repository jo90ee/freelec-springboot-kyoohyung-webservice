package com.kyoohyung.book.springboot.config.auth.Dto;

import com.kyoohyung.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;


/**
 * 인증된 사용자 정보만 필요하다, 그외의 정보는 관리 하지 않는다.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();


    }
}
