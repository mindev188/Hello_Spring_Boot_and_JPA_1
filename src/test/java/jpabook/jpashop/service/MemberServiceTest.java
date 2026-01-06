package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
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

        // WHEN

        // THEN
    }
}