package hello.core.member;
//회원의 Entity

public class Member {
    private Long id;
    private String name;
    private Grade grade;

    //회원의 인스턴스를 생성해줄 회원의 생성자
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    //회원 데이터를 가지고 오는 getter 저장해 줄 setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

}
