package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        //현재 빈으로 등록된 memoryMemberRepository 와 같은 빈을 수동 등록.
        //테스트 결과 Overriding bean definition for bean 'memoryMemberRepository' with a different definition 확인
        // 자동 등록과 수동 등록 되는 빈이 같을 경우 수동 등록 빈에 우선권을 주지만 애플리케이션을 실행하면 실행을 실패 시킨다.
        // ( 개발자의 의도로 빈을 생성할 경우 나중에 야기될 예외 상황을 미리 방지 )
        //  수동 등록 빈으로 사용하고 싶다면 application.properties 에
        //  빈 재정의를 활성화 시키는 설정인 spring.main.allow-bean-definition-overriding=true를 추가 해야 한다.

    }
}
