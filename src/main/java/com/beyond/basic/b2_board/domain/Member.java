package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString //디버깅용
//JPA의 엔티티매니저에게 객체를 위임하려면 @Entity어노테이션 필요
@Entity
public class Member extends BaseTimeEntity{
    @Id //pk설정
//    IDENTITY : auto_increment설정(AUTO설정은 jpa에게 적절한 전략을 위임하는것)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    String은 별다른 설정없을경우 varchar(255)로 DB컬럼으로 설정. 변수명==컬럼명으로 변환.
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
//    @Column(name = "pw") 이렇게 할수는 있으나, 되도록이면 컬럼명과 변수명을 일치시키는것이 개발의 혼선줄임.
    private String password;

    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePw(String newPassword){
        this.password = newPassword;
    }

    public MemberListRes listFromEntity(){
        return new MemberListRes(this.id, this.name, this.email);
    }
    public MemberDetailDto detailFromEntity(){
        return new MemberDetailDto(this.name, this.email, this.password);
    }
}
