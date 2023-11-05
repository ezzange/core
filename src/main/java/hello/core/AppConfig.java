package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberverServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RequiredArgsConstructor
@Configuration // 주석처리하면 스프링 컨테이너가 객체를 생설할 때 싱글톤으로 객체를 생성하지 못해 call AppConfig.memberRepository는 세번 호출된다. => 싱글톤 보장 X
public class AppConfig {
    //@Bean memberService -> MemberverServiceImpl -> memberRepository -> MemoryMemberRepository
    //@Bean orderService -> OrderServiceImpl -> MemoryMemberRepository , ...

    //@Primary, @Qualifier
    //코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈이 있고,
    // 코드에서 특별한 기능으로 가끔 사 용하는 서브 데이터베이스의 커넥션을 획득하는 스프링 빈이 있다고 생각해보자.
    // 메인 데이터베이스의 커넥션을 획득하 는 스프링 빈은 @Primary 를 적용해서 조회하는 곳에서
    // @Qualifier 지정 없이 편리하게 조회하고, 서브 데이터베 이스 커넥션 빈을 획득할 때는 @Qualifier 를 지정해서
    // 명시적으로 획득 하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다.
    // 물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 @Qualifier 를 지정해주는 것은 상관없다.
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
