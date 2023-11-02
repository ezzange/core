package hello.core.member;
//회원 저장소
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
