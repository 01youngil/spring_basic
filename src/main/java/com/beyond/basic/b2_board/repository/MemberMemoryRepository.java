package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberMemoryRepository {
    private List<Member> memberLsit = new ArrayList<>();
    public static Long id = 1L;

    public List<Member> findAll(){
       return this.memberLsit;
    }

    public void save(Member member){
        this.memberLsit.add(member);
        id++;
    }
}
