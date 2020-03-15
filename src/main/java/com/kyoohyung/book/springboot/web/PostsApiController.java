package com.kyoohyung.book.springboot.web;

import com.kyoohyung.book.springboot.web.dto.PostsListResponseDto;
import com.kyoohyung.book.springboot.web.dto.PostsResponseDto;
import com.kyoohyung.book.springboot.web.dto.PostsSaveRequestDto;
import com.kyoohyung.book.springboot.web.dto.PostsUpdateRequestDto;
import com.kyoohyung.book.springboot.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);

    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto updateRequestDto){
        return postsService.update(id, updateRequestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}
