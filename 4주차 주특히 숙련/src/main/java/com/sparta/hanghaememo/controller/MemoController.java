//메서드가 들어가는 파일


package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.dto.ResponseMsgDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController  //@Controller에 @ResponseBody가 추가된 것입니다. 당연하게도 RestController의 주용도는
                // Json 형태로 객체 데이터를 반환하는 것입니다.

@RequiredArgsConstructor     //@RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
                            //새로운 필드를 추가할 때 다시 생성자를 만들어서 관리해야하는 번거로움을 없애준다.
public class MemoController {

    private final MemoService memoService;      //???

    @GetMapping("/")  //
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    //메모장 만들기
    @PostMapping("/api/memos")  //post 방식알 body가 있고, 그 안에 저장해야하는 값이 있기 때문에 requestbody 사용.
                                    //POST 요청을 하는 API의 어노테이션
                                            //데이터를 게시할 때 사용한다
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        //MemoRequestDto 객체로 받는다.
        return memoService.createMemo(requestDto,request);
    }


    @GetMapping("/api/memos")    //GET 요청을 하는 API의 어노테이션. 데이터를 가져올 때 사용한다
    public List<Memo> getMemos() {
        return memoService.getMemos();

    }



    @GetMapping("/api/memo/{id}") //@RequestParam 받는 값이 api/memo?id=3 , @PathVariable 받는 값이 api/memo/3
    public MemoResponseDto getMemo(@PathVariable Long id){

        return memoService.getMemo(id);
    }

    // * 스프링에서 대표적으로 데이터를 전달해 줄 방법 : @PathVariable , @RequestParam
    //@PathVariable : URI 경로의 일부를 파라미터로 사용할 때 이용(URI 경로에서 값을 가져온다)  , 받는 값 : api/memo/3
                     //값을 하나만 받아올 수 있다.

    //@RequestParam(required=false, value={변수명}) : uri를 통해 전달된 값을 파라미터로 받아오는 역할 , 받는 값 : api/memo?id=3
                                                    //쿼리스트링 등을 이용한 여러 개 데이터를 받아올 때 쓴다.






    @PutMapping("/api/memos/{id}")   //PUT 요청을 하는 API의 어노테이션, 데이터를 수정할 때 사용한다. (전체)
                                         //@PatchMapping : PATCH 요청을 하는 API의 어노테이션, 데이터를 수정할 때 사용한다. (부분)

    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        return memoService.update(id, requestDto, request);

    }
    //스프링에서 비동기 처리를 하는 경우 @RequestBody , @ResponseBody를 사용한다.
    //비동기통신을 하기위해서는 클라이언트에서 서버로 요청 메세지를 보낼 때, 본문에 데이터를 담아서 보내야 하고,
    // 서버에서 클라이언트로 응답을 보낼때에도 본문에 데이터를 담아서 보내야 한다. 이 본문이 바로 body 이다.
    //즉, 요청본문 requestBody, 응답본문 responseBody 을 담아서 보내야 한다. 이때 비동기식 클라-서버 통신을 위해 JSON 형식의 데이터를 주고받는 것이다.

    //@RequestBody :http요청의 본문(body)이 그대로 전달된다.클라이언트에서 서버로 필요한 데이터를 요청하기 위해 JSON 데이터를 요청 본문에 담아서 서버로 보내면,
    // 서버에서는 @RequestBody 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바객체로 변환시켜, 객체에 저장한다.


    //@ResponseBody : 자바객체를 HTTP요청의 바디내용으로 매핑하여 클라이언트로 전송한다.
                      // 즉, @Responsebody 어노테이션을 사용하면 http요청 body를 자바 객체로 전달받을 수 있다.
                    //서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송한다.



    @DeleteMapping("/api/memos/{id}")   //
    public ResponseMsgDto deleteMemo(@PathVariable Long id,HttpServletRequest request){ //중요한 정보를 줄 ㅗ받음
        memoService.deleteMemo(id,request);
        return new ResponseMsgDto("삭제 완료!", HttpStatus.OK.value());
    }
}

