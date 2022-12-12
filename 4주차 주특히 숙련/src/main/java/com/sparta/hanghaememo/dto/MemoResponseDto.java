

package com.sparta.hanghaememo.dto;


import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor  // 기본 생성자를 생성해준다.
//
//이 경우 초기값 세팅이 필요한 final 변수가 있을 경우 컴파일 에러가 발생함으로 주의한다.
//
//@NoArgsConstructor(force=true) 를 사용하면 null, 0 등 기본 값으로 초기화 된다.
public class MemoResponseDto {


//    private String msg;
//    private int statusCode;
    private String username;   //객체
    private String contents;
    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
//    private String dates;

    private Long id;

    public MemoResponseDto(Memo memo) {         // Memo memo
        this.username = memo.getUsername();       //this ??
        this.contents = memo.getContents();
        this.title = memo.getTitle();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.id = memo.getId();
//        this.dates = memo.getDates();

    }
//
//    public MemoResponseDto(String msg, int statusCode) {        // msg, statusCode ?
//        this.msg = msg;
//        this.statusCode = statusCode;
//    }

}