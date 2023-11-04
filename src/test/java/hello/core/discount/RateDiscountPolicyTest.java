package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 고정 할인 적용")
    void vipOk() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닌 경우 10% 할인 미적용")
    void VipNO() {
        //given
        Member member = new Member(2L, "memberB", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
//        assertThat(discount).isEqualTo(1000); 실행 오류 나야함.
        assertThat(discount).isNotEqualTo(1000);
    }
}