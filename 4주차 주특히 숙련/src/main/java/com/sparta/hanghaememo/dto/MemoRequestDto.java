package com.sparta.hanghaememo.dto;
//DTO(Data Transfer Object)란 계층간 데이터 교환을 위해 사용하는 객체(Java Beans)입니다.  여기서는 객체를 만듬!
//getter & setter 메소드만 가진 클래스

import lombok.Getter;
import lombok.NoArgsConstructor;  //매개변수가 없는 기본 생성자를 생성 // 매개변수란? :함수의 매개변수(parameter)란 함수를 호출할 때 인수로 전달된 값을 함수 내부에서 사용할 수 있게 해주는 변수입니다
import lombok.Setter;

@Getter     //외부에서 객체의 데이터를 읽을 때도 메소드를 사용하는 것이 좋다.
@Setter   //외부에서 메소드를 통해 데이터에 접근하도록 유도한다.
@NoArgsConstructor
public class MemoRequestDto {
   // private String username;   //객체
    private String contents;
    private String title;
    //private String password;


}

//    @NotBlank(message = "비밀번호 확인을 입력해주세요")
//    private String checkpw;
//
//    }