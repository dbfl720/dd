package com.sparta.hanghaememo.repository;



import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();  //내림차순으로 모든 정보를 저장한다.

    Optional<Object> findByIdAndUserId(Long id, Long userId);
}