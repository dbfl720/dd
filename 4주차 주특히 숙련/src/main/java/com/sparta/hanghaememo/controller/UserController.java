package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.LoginRequestDto;
import com.sparta.hanghaememo.dto.ResponseMsgDto;
import com.sparta.hanghaememo.dto.SignupRequestDto;
import com.sparta.hanghaememo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller  //@Controller의 역할은 Model 객체를 만들어 데이터를 담고 View를 반환하는 것, @RestController는 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아 전송
@RequiredArgsConstructor   //이 어노테이션은 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다. 주로 의존성 주입(Dependency Injection) 편의성을 위해서 사용되곤 합니다.
@RequestMapping("/api/auth")
//우리는 특정 uri로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의를 한다.
//이때 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것이 @RequestMapping이다.
//@RequestMapping에서 가장 많이사용하는 부분은 value와 method이다. (더 많지만 여기서는 여기까지만)
//value는 요청받을 url을 설정하게 된다. method는 어떤 요청으로 받을지 정의하게 된다.(GET, POST, PUT, DELETE 등)


public class UserController {
    //생성자 선언 : 클래스(매개변수선언,...)
    private final UserService userService;   //의존성 주입



    @ResponseBody
    @PostMapping("/signup")  //회원가입 구현
    public ResponseMsgDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new ResponseMsgDto("회원가입 성공!", HttpStatus.OK.value()); //요청 성공표시.
    }


//    @PostMapping("/login")   //로그인 구현
//    public String login(LoginRequestDto loginRequestDto) {
//        userService.login(loginRequestDto);
//        return "redirect:/api/memos";
//    }




    //클라이언트 -> 서버 요청 : @RequestBody
    //서버 -> 클라이언트 응답 : @ResponseBody  , 자바 객체를 json 기반의 HTTP Body로 변환
    @ResponseBody   //로그인 구현 , ajax에서 body쪽에 값이 넘어가기 때문에, 아래 requestbody를 써줘야 된다.
    @PostMapping("/login")
    public ResponseMsgDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {  /// ???????
        userService.login(loginRequestDto, response);   //HttpRequest에서 header가 넘어와서 받아오는 것처럼, Client쪽으로 반환할 때는 response객체를 반환하다.반환 할 response Header에 우리가 만들어준 토큰을 넣어주기 위해서 받아 오고 있다.
        return new ResponseMsgDto("로그인 성공!", HttpStatus.OK.value());
    }



   // 예를 들어서 클라이언트에서 서버에 JSON 형식의 requestBody로 요청 데이터를 전송했을 때, Java에서는 해당 JSON 형식의 데이터를 받기 위해서 JSON -> Java Object로의 변환이 필요합니다.
   // 마찬가지로 요청된 데이터를 처리 후, 서버에서 클라이언트로 다시 응답 데이터 responseBody를 보낼 때도 Java Object에서 JSON 또는 XML 같은 형식으로의 변환이 필요합니다. 이러한 과정을 해당 어노테이션들이 처리해주는 것입니다.









    @GetMapping("user-usernames/{username}exists") //중복된 회원가입 확인
    public ResponseEntity<Boolean> checkUsernameDuplicate(@PathVariable String username) {
        return ResponseEntity.ok(userService.checkUsernameDuplicate(username));
    }

    // * 스프링에서 대표적으로 데이터를 전달해 줄 방법 : @PathVariable , @RequestParam
    //@PathVariable : URI 경로의 일부를 파라미터로 사용할 때 이용(URI 경로에서 값을 가져온다)  , 받는 값 : api/memo/3
    //값을 하나만 받아올 수 있다.

    //@RequestParam(required=false, value={변수명}) : uri를 통해 전달된 값을 파라미터로 받아오는 역할 , 받는 값 : api/memo?id=3
    //쿼리스트링 등을 이용한 여러 개 데이터를 받아올 때 쓴다.








    @GetMapping("/getsign")
    public ModelAndView signupPage() {
        return new ModelAndView("sign");
    }

    @GetMapping("/getlogin")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }






}