package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.CommentDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.entity.Comment;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.entity.UserRoleEnum;
import com.sparta.hanghaememo.exception.ErrorCode;
import com.sparta.hanghaememo.exception.RequestException;
import com.sparta.hanghaememo.jwt.JwtUtil;
import com.sparta.hanghaememo.repository.MemoRepository;
import com.sparta.hanghaememo.repository.CommentRepository;
import com.sparta.hanghaememo.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public CommentDto addComment(CommentDto commentDto, Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new RequestException(ErrorCode.BAD_TOKEN_400);
            }
            Optional<Memo> optionalBoard = memoRepository.findById(id);
            Memo memo = optionalBoard.orElseThrow(
                    () -> new RequestException(ErrorCode.NULL_CONTENTS_400)
            );

            Comment comment = Comment.builder()
                    .commentId(commentDto.getCommentId())
                    .memo(memo)
                    .commentUsername(claims.getSubject())
                    .commentContents(commentDto.getCommentContents())
                    .build();

            return new CommentDto(commentRepository.save(comment));
        }
        throw new RequestException(ErrorCode.NULL_TOKEN_400);
    }



    @Transactional
    public CommentDto updateComment(CommentDto commentDto, Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new RequestException(ErrorCode.BAD_TOKEN_400);
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new RequestException(ErrorCode.NULL_USER_400)
            );

            Optional<Comment> optionalCommnet = commentRepository.findById(id);
            Comment comment = optionalCommnet.orElseThrow(
                    () -> new RequestException(ErrorCode.NULL_COMMENT_400)
            );

            if (comment.getCommentUsername().equals(claims.getSubject())) {
                comment.update(commentDto);
                return new CommentDto(commentRepository.save(comment));
            } else if (user.getRole() == UserRoleEnum.ADMIN) {
                comment.update(commentDto);
                return new CommentDto(commentRepository.save(comment));
            }else {
                throw new RequestException(ErrorCode.NULL_USER_ACCESS_400);
            }
        }
        throw new RequestException(ErrorCode.NULL_TOKEN_400);
    }



    public CommentDto deleteComment(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new RequestException(ErrorCode.BAD_TOKEN_400);
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new RequestException(ErrorCode.NULL_USER_400)
            );

            Optional<Comment> optionalCommnet = commentRepository.findById(id);
            Comment comment = optionalCommnet.orElseThrow(
                    () -> new RequestException(ErrorCode.NULL_COMMENT_400)
            );

            if (comment.getCommentUsername().equals(claims.getSubject())) {
                commentRepository.delete(comment);
                return new CommentDto(comment);
            }else if (user.getRole() == UserRoleEnum.ADMIN) {
                commentRepository.delete(comment);
                return new CommentDto(comment);
            }else {
                throw new RequestException(ErrorCode.NULL_USER_ACCESS_400);
            }
        }
        throw new RequestException(ErrorCode.NULL_TOKEN_400);
    }
}
