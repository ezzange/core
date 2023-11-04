package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

//    MemberService memberService = new MemberverServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 30000);
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.getDiscountPrice()).isNotEqualTo(1000);
    }


    @Test
    void fieldInjectionTest(){
        OrderServiceImpl orderService1 = new OrderServiceImpl();

        orderService1.setMemberRepository(new MemoryMemberRepository());
        orderService1.setDiscountPolicy(new FixDiscountPolicy());

//        orderService1.createOrder(1L, "itemA", 10000);



    }
}
