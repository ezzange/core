package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자가 10000원 주문
        int userBPrice = statefulService2.order("userB", 20000);


        //ThreadA : A사용자 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        //A사용자의 주문 금액을 조회 하는데 B사용자의 주문 금액이 나온 이유는 인스턴스 변수 값을 공유하기 때문이다.
        //가장 마지막으로 할당된 값이 전체적으로 공유 되어 버린다.
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}