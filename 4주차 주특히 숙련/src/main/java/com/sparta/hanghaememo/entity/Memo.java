

package com.sparta.hanghaememo.entity;



//import com.example.hanghaememo.dto.MemoRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaememo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;   //
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {  //Timestamped가 상속이 되어서 같이 사용 될 것임.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;        //@Id만 사용할 경우 기본 키를 직접 할당해 주어야 합니다.
                          //기본 키를 직접 할당하는 대신 데이터베이스가 생성해주는 값을 사용하려면 @GeneratedValue를 사용해주면 됩니다.
                          //기본 설정 값으로 각 데이터베이스에 따라 기본키를 자동으로 생성한다.
                          //기본키의 제약조건
                          //null이면 안된다.
                         //유일하게 식별할 수 있어야한다.
                         //변하지 않는 값이어야 한다.


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;


    @Column(nullable = false)
    private String title;


//    @Column(nullable = false)
//    private String dates;


//    @Column(nullable = false)
//    private String password;

//    public Memo(String username, String contents) {
//        this.username = username;
//        this.contents = contents;
//    }
    @Column(nullable = false)
    private Long userId;



    @JsonIgnore
    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();




    public Memo(MemoRequestDto requestDto, Long userid, String username) {  //생성자
        this.username = username;
        this.contents = requestDto.getContents();  //this :
        this.title = requestDto.getTitle();
//        this.password = requestDto.getPassword();
        this.userId = userid;
    }

    public void update(MemoRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
//        this.userId = userid;
//        this.password = requestDto.getPassword();


    }
}
//
//
//    public void update(MemoRequestDto memoRequestDto) {
//        this.username = memoRequestDto.getUsername();
//        this.contents = memoRequestDto.getContents();
//    }
//
//
