package com.beyond.basic.b2_board.controller;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.beyond.basic.b2_board.dtos.*;
import com.beyond.basic.b2_board.service.MemberService;
import com.beyond.basic.b2_board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post/rest")
public class PostRestController {

    private final PostService postService;
    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> postlist(){
        List<PostListRes> postListResList = postService.findAll();
        return new ResponseEntity<>(new CommonDto(
                HttpStatus.OK.value(), "postlist is found",postListResList
                ),HttpStatus.OK);
    }

    //    글상세조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id){
        PostDetailDto detailDto = postService.findById(id);
        return new ResponseEntity<>(new CommonDto(
                HttpStatus.OK.value(),"post is found",detailDto
                ),HttpStatus.OK);
    }

    //    글등록
    @PostMapping("/create")
//    Valid와 NotEmpty 등 검증 어노테이션이 한쌍
    public ResponseEntity<?> postCreatePost(@Valid @RequestBody PostCreateDto postCreateDto){
//        Post post = postService.save(postCreateDto);
        postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(HttpStatus.CREATED.value(), "post is created","OK"), HttpStatus.CREATED);
    }
}
