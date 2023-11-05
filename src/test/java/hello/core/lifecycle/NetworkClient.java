package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//초기화, 소멸 인터페이스 사용  implements InitializingBean, DisposableBean
public class NetworkClient{
    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call : "+url+"message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close :" + url);
    }
    //annotation을 사용한 빈의 소멸, 초기화  - 외부 라이브러리에 적용 불가 외부 라이브러리를 초기화, 종료 해야한다면 @Bean의 기능 사용
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

