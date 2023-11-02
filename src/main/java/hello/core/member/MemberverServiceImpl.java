package hello.core.member;
//회원의 구현체
public class MemberverServiceImpl implements MemberService {

                                                    //구현객체 선택
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //인터페이스를 의존 하지만 실제 할당하는 부분은 구현체를 의존
    //=> DIP(개방폐쇄원칙) 위반

    private final MemberRepository memberRepository;
    public MemberverServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //외부에서 생성자를 통해서 멤버레포지토리의 구현체가 뭐가 들어갈 지 정해주기 위해
    //=> 생성자 주입

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
