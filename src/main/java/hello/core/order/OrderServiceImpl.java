package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//고정 할인
//    private final DiscountPolicy rateDis countPolicy = new RateDiscountPolicy();//변동 할인
//    구현체를 바꾸는 순간 개발자가 직접 코드를 변경해 주어야 한다. => DIP 위반.
//    클라이언트는 인터페이스 뿐만 아니라 구현체도 의존하고 있다. => OCP 위반.

//    private DiscountPolicy discountPolicy;
    //구현체에만 의존하지 않고 인터페이스만 의존하는 코드 이지만 해당 코드로는 원하는 구현체로 연결되지 않아 nullpoint예외가 발생한다.
    //이 문제를 해결하기 위해서는 개발자대신 클라이언트의 구현 객체를 대신 생성하고 주입해줄 중간조율자가 필요하다.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //DIP 규칙을 올바르게 지키고 있다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
