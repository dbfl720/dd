package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class StatusResponseDto {
    private Boolean success;
    private int statusCode;
    private String msg;

    public StatusResponseDto(Boolean success, int statusCode, String msg) {
        this.success = success;
        this.statusCode = statusCode;
        this.msg = msg;
    }
}