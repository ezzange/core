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

@Configuration // 주석처리하면 스프링 컨테이너가 객체를 생설할 때 싱글톤으로 객체를 생성하지 못해 call AppConfig.memberRepository는 세번 호출된다. => 싱글톤 보장 X
public class AppConfig {
    //@Bean memberService -> MemberverServiceImpl -> memberRepository -> MemoryMemberRepository
    //@Bean orderService -> OrderServiceImpl -> MemoryMemberRepository , ...

    @Bean
    public MemberService memberService() { // memberService의 구현은 memberServiceImpl가 할 것이다.
        System.out.println("call AppConfig.MemberService");
        return new MemberverServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() { // memberRepository의 구현은 MemoryMemberRepository가 할 것이다.
        System.out.println("call AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() { // orderService의 구현은 OrderServiceImpl.
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
