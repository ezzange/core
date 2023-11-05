package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//고정 할인
//    private final DiscountPolicy rateDis countPolicy = new RateDiscountPolicy();//변동 할인
//    구현체를 바꾸는 순간 개발자가 직접 코드를 변경해 주어야 한다. => DIP 위반.
//    클라이언트는 인터페이스 뿐만 아니라 구현체도 의존하고 있다. => OCP 위반.

//    private DiscountPolicy discountPolicy;
    //구현체에만 의존하지 않고 인터페이스만 의존하는 코드 이지만 해당 코드로는 원하는 구현체로 연결되지 않아 nullpoint예외가 발생한다.
    //이 문제를 해결하기 위해서는 개발자대신 클라이언트의 구현 객체를 대신 생성하고 주입해줄 중간조율자가 필요하다.

    //DIP 규칙을 올바르게 지키고 있다. ( 수정자 주입을 위해 주석 처리)
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;

//     private  MemberRepository memberRepository;
//     private  DiscountPolicy discountPolicy;
//    public OrderServiceImpl(){
//        //기본 생성자
//    }

    //생성자 주입 => 생성자를 호출하면서 빈을 등록하고 의존관계를 같이 주입한다.
    @Autowired (required = false)//주입할 대상이 없어도 동작하게 하 려면 `@Autowired(required = false)` 로 지정
    //생성자가 하나일 경우 생략
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy ratediscountPolicy) { //생성자 주입
        System.out.println("생성자 주입 | memberRepository = " + memberRepository);
        System.out.println("생성자 주입 | ratediscountPolicy = " + ratediscountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = ratediscountPolicy;
    }


// 필드에 직접 주입
//    @Autowired private  MemberRepository memberRepository;
//    @Autowired private  DiscountPolicy discountPolicy;

//    메서드를 통한 수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("메서드를 통한 생성자 주입 | memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("메서드를 통한 생성자 주입 | discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

//    //일반 메서드 주입 메서드 에 @AutoWired를 붙여주는 것으로 한번에 여러 필드를 주입 받을 수 있다.
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //singleton test 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

