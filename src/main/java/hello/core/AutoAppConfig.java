package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //예제코드를 삭제하지 않고 진행하기 위해 설정.
//        basePackages = "hello.core.member", //탐색을 시작할 패키지를 지정해줄 수 있다. 관례상 패키지 위치를 지정하지 않고,  설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //같은 이름으로 수동 등록
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    @Bean
//    OrderServiceImpl orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }
}

