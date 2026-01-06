package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        // GIVEN
        Member member = new Member();
        member.setName("kim");

        // WHEN
        Long saveId = memberService.join(member);

        // THEN
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }


    @Test
    public void 중복_회원_예외() throws Exception {
        // GIVEN
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // WHEN
        memberService.join(member1);

        // THEN
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}