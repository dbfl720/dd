package com.sparta.hanghaememo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseMsgDto {


    private String msg;
    private int status;


}

//    public ResponseMsgDto(String msg, int status) {   //response  생성자
//        this.msg = msg;
//        this.status = status;
//    }
//}
