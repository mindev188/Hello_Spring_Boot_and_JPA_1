package jpabook.jpashop;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

//import static org.junit.Assert.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired  MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // GIVEN
        Member member = new Member();
        member.setUserName("memberA");

        // WHEN
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // THEN
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}