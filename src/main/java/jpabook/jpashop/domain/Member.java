package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member") // 오더 테이블에 있는 멤버 필드에 의해서 매핑 된 것이다. 거울일 뿐이야!
    private List<Order> orders = new ArrayList<>();

    public void setUsername(String memberA) {
    }
}
