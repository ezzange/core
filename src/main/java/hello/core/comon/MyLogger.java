package hello.core.comon;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
//동시에 오는 여러 http 요청을 각 각의 객체로 구분하기 위해 uuid와 provider 사용
//http 요청과 상관없이 가짜 프록시를 만들어두고 진짜 요청이 왔을 때 만들어진 빈을 꺼내 준다.(진짜 객체 조회를 필요한 시점까지 지연처리)
//컨트롤러에서 mylogger의 클래서명을 가져오면 MyLogger$$EnhancerBySpringCGLIB$$c5e757f2 이런식으로 CGLIB 에서 만들어진다.

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
    public void log(String message) {
        uuid = UUID.randomUUID().toString();
        System.out.println(" [ "+uuid+" ] "+" [ "+requestURL+" ] "+  message);

    }
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println(" [ " +uuid+" ] request scope bean create"+ this);
    }
    @PreDestroy
    public void close() {
        System.out.println(" [ " +uuid+" ] request scope bean close"+ this);
    }
}
