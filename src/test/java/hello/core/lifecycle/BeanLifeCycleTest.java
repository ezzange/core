package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//bean lifeCycle 객체 생성 -> 의존관계 주입
public class BeanLifeCycleTest {

    @Test
    public void lifecycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {
        // 빈 등록 초기화, 소멸 메서드 등록 @Bean(initMethod = "init",destroyMethod = "close")
        //@Bean 의 destroyMethod 는 이름을 지정하지 않아도 close와 shutdown이란 이름의 메서드를 추론해서 호출해준다.
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
