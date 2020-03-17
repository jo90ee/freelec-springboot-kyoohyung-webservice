package com.kyoohyung.book.springboot.web;

import com.kyoohyung.book.springboot.config.auth.Dto.SessionUser;
import com.kyoohyung.book.springboot.config.auth.LoginUser;
import com.kyoohyung.book.springboot.web.dto.PostsResponseDto;
import com.kyoohyung.book.springboot.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/") //INDEX화면으로 데이터를 넣어줌
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");// @LoginUser를 적용함으로 이 부분을 주석 처리함  이제 어느 컨트롤러든지  @LoginUser만 사용하면 세션 정보를 가져올 수 있게 된다.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
