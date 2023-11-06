package hello.core.comon;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
//동시에 오는 여러 http 요청을 각 각의 객체로 구분하기 위해 uuid와 provider 사용

@Component
@Scope(value = "request")
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
