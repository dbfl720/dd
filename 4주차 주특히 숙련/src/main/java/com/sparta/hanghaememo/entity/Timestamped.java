package com.sparta.hanghaememo.entity;



        import lombok.Getter;
        import org.springframework.data.annotation.CreatedDate;
        import org.springframework.data.annotation.LastModifiedDate;
        import org.springframework.data.jpa.domain.support.AuditingEntityListener;

        import javax.persistence.EntityListeners;
        import javax.persistence.MappedSuperclass;
        import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    @CreatedDate   //생성된 시간 정보
    private LocalDateTime createdAt;    //여기서 createdAt가 h2에 들어감.

    @LastModifiedDate  //수정된 시간 정보
    private LocalDateTime modifiedAt;
}