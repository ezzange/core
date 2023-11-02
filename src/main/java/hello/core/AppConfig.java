package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberverServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() { // memberService의 구현은 memberServiceImpl가 할 것이다.
        return new MemberverServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() { // memberRepository의 구현은 MemoryMemberRepository가 할 것이다.
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() { // orderService의 구현은 OrderServiceImpl.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
