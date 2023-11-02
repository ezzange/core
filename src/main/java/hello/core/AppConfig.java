package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberverServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//구현 객체를 생성하고 인스턴스 객체를 생성자 주입으로 연결해준다.
//구현체가 변경될 경우 여가의 코드만 변경 해주면 된다.
public class AppConfig {
    //메서드명과 리턴타입만 봐도 역할과 구현 클래스가 한 눈에 들어와 애플리케이션의 전체 구성을 한 번에 파악할 수 있다.
    public MemberService memberService() { // memberService의 구현은 memberServiceImpl가 할 것이다.
        return new MemberverServiceImpl(memberRepository());
    }

    public MemoryMemberRepository memberRepository() { // memberRepository의 구현은 MemoryMemberRepository가 할 것이다.
        return new MemoryMemberRepository();
    }

    public OrderService orderService() { // orderService의 구현은 OrderServiceImpl.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); //discountPolicy의 구현은 FixDiscountPolicy.
        return new RateDiscountPolicy(); // discountPolicy의 구현은 RateDiscountPolicy.
    }
}
