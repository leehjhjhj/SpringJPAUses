package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.IllegalChannelGroupException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) // Junit 설정할 때, 스프링도 실행할래!
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입 () throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveID = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveID));
    }
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외 () throws Exception {

        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야 합니다.");
    }
}