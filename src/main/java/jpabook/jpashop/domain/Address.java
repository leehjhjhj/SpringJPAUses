package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) { // 여기는 왜 static이 안붙는지?
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
