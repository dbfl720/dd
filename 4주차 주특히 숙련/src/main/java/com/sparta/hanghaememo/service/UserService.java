package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.LoginRequestDto;
import com.sparta.hanghaememo.dto.SignupRequestDto;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.entity.UserRoleEnum;
import com.sparta.hanghaememo.jwt.JwtUtil;
import com.sparta.hanghaememo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;



@Service  //Service 를 구현할때 크게 2가지가 있어요.  1) Service Interface 만들기  2) Service Interface 구현한 Class 만들기


@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;  //의존성 주입??????
    private final JwtUtil jwtUtil;  //의존성 주입

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    @Transactional  //DB 등의 트랜잭션 처리를 위해 사용되는 어노테이션 이다. 일일이 프로그래밍으로 작업하던 것을 좀 쉽게 해준다. db connection을 얻은이후 일일이 transaction begin, commit 해주고 특정 예외 발생시 rollback해주던 부분을 자동으로 손쉽게 해주니 편하긴 하다.
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");   //new를 붙인 이유..????
        }

//        String email = signupRequestDto.getEmail();
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password,  role);
        userRepository.save(user);
    }

//    @Transactional(readOnly = true)
//    public void login(LoginRequestDto loginRequestDto) {
//        String username = loginRequestDto.getUsername();
//        String password = loginRequestDto.getPassword();
//
//        // 사용자 확인
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
//        );
//
//        // 비밀번호 확인
//        if(!user.getPassword().equals(password)){
//            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//    }


    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }





    @Transactional  //데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터 추가, 갱신, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다. 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }      //중복된 username 확인



}
