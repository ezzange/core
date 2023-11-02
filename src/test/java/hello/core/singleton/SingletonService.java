package hello.core.singleton;

public class SingletonService {

    //static 영역에 객체 생성
    private static final SingletonService instance = new SingletonService();

    //객체 인스턴스가 필요하면 조회할 수 있는 public static 메서드
    public static SingletonService getInstance() { //이 메서드는 호출하면 항상 같은 객체를 반환한다.
        return instance;
    }

    //외부에서 생성하지 못하도록 기본생성자를 private로 접근제한.
    private SingletonService(){

    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
