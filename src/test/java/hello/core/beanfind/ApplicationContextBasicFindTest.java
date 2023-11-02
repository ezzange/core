package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberverServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberverServiceImpl.class);
                  //instanceof 연산자는 객체 가 지정된 유형(클래스, 하위 클래스 또는 인터페이스)의 인스턴스인지 여부를 테스트하는 데 사용
    }

    @Test
    @DisplayName("빈 이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberverServiceImpl.class);
    }
    //타입으로 조회시 동일한 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이럴 경우 빈 이름을 지정해주면 된다.

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberverServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberverServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 조회 실패 사례")
    void findBeanNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); // 예외 발생

        //Junit에서 제공하는 assertThrows : 예외를 던지면 테스트 성공
        assertThrows(NoSuchBeanDefinitionException.class, // 람다식 문장 실행시 NoSuchBeanDefinitionException 발생.
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
