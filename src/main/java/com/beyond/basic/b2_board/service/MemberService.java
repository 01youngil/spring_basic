package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import com.beyond.basic.b2_board.repository.MemberJdbcRepository;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//아래 어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고, 만약 예외(unchecked)가 발생시 롤백처리 자동화
@Transactional
public class MemberService {

    @Autowired
    private MemberJdbcRepository memberRepository;

    public List<MemberListRes> findAll(){
//        List<Member> members = memberMemoryRepository.findAll();
//        List<MemberListRes> memberListRes = new ArrayList<>();
//        for(Member m : members){
//            memberListRes.add(m.listFromEntity());
//        }
//        return memberListRes;
        return memberRepository.findAll().stream().map(m -> m.listFromEntity()).collect(Collectors.toList());
    }

    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{
        if(memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if(memberCreateDto.getPassword().length()<8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        memberRepository.save(memberCreateDto.toEntity());
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException, RuntimeException{
//        Optional<Member> optionalMember = memberMemoryRepository.findById(id);
//        Member member = optionalMember.orElseThrow(() -> new NoSuchElementException("없는 ID입니다."));
//        MemberDetailDto dto = member.detailFromEntiry();
//        return dto;
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("없는 ID입니다."))
                .detailFromEntiry();
    }
}
