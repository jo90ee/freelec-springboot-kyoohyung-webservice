package com.kyoohyung.book.springboot.web.service;


import com.kyoohyung.book.springboot.domain.posts.Posts;
import com.kyoohyung.book.springboot.domain.posts.PostsRepository;
import com.kyoohyung.book.springboot.web.dto.PostsListResponseDto;
import com.kyoohyung.book.springboot.web.dto.PostsResponseDto;
import com.kyoohyung.book.springboot.web.dto.PostsSaveRequestDto;
import com.kyoohyung.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto updateRequestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(updateRequestDto.getTitle(), updateRequestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
    @Transactional(readOnly = true)// (readOnly = true ) 옵션은 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true ) // (readOnly = true ) 옵션은 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
