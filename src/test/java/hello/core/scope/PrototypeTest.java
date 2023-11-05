package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find PrototypeBean1");
        PrototypeBean PrototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find PrototypeBean2");
        PrototypeBean PrototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean1 = " + PrototypeBean1);
        System.out.println("PrototypeBean2 = " + PrototypeBean2);
        assertThat(PrototypeBean1).isNotSameAs(PrototypeBean2);
        ac.close();
        //prototype은 스프링 컨테이너가 생성및 초기화만 하고 관리하지 않기 때문에 요청 받을 때마다 생성하고 버리기 때문에 종료메서드가 필요 없다.
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");

        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
