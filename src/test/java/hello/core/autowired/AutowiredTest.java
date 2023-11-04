package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

//의존관계 주입 옵션처리 테스트
public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        //@Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        @Autowired(required = false)
                                //스프링 빈으로 관리 되는 게 아닌 클래스를 넣어준다.
        public void setNoBean1(Member noBean1) {
            System.out.println("setNoBean1 = " + noBean1);
        }// 호출 안됨
        //org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("setNoBean2 = " + noBean2);
        }//null 호출
        //Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("setNoBean3 = " + noBean3);
        }//Optional.empty 호출

    }
}
