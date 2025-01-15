package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString //디버깅용
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;

    public MemberListRes listFromEntity(){
        return new MemberListRes(this.id, this.name, this.email);
    }
    public MemberDetailDto detailFromEntiry(){
        return new MemberDetailDto(this.name, this.email, this.password);
    }
}
