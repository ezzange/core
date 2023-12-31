package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class singletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singletonBean.class);

        singletonBean singletonBean1 = ac.getBean(singletonBean.class);
        singletonBean singletonBean2 = ac.getBean(singletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isEqualTo(singletonBean2);
        ac.close();
    }

    @Scope("singleton")
    static class singletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");

        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
