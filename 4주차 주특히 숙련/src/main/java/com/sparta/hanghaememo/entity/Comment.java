package com.sparta.hanghaememo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaememo.dto.CommentDto;
import com.sparta.hanghaememo.entity.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @JsonIgnore  //??
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   //?
    @JoinColumn(name = "bId")
    private Memo memo;

    @Column(nullable = false)
    private String commentContents;

    @Column(nullable = false)
    private String commentUsername;

    public void update(CommentDto commentDto) {
        this.commentContents = commentDto.getCommentContents();
    }
}