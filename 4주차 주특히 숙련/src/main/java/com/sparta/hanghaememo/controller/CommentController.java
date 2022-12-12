

package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.CommentDto;
import com.sparta.hanghaememo.dto.ResponseMsgDto;
import com.sparta.hanghaememo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 추가
    @PostMapping("/comment/{id}")
    public CommentDto addComment(@RequestBody CommentDto commentDto, @PathVariable Long id, HttpServletRequest request) {
        return commentService.addComment(commentDto, id, request);
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    public CommentDto updateComment(@RequestBody CommentDto commentDto, @PathVariable Long id, HttpServletRequest request) {
        return commentService.updateComment(commentDto, id, request);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ResponseMsgDto> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        commentService.deleteComment(id, request);
        return ResponseEntity.ok(new ResponseMsgDto("삭제 성공",HttpStatus.OK.value()));
    }
}