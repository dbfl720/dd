package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long mId;
    private String commentUsername;
    private String commentContents;

    public CommentDto(Comment comment){

        this.commentId = comment.getCommentId();
        this.mId = comment.getMemo().getId();
        this.commentUsername = comment.getCommentUsername();
        this.commentContents = comment.getCommentContents();
    }
}