package hello.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다. 의존 관계 설정 시 편리.
public class LombokTest {

    private String name;
    private int age;

    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest();
        lombokTest.setName("ezzange");

        String name = lombokTest.getName();
        System.out.println("name = " + name);
        System.out.println("lombokTest = " + lombokTest);
    }
}
