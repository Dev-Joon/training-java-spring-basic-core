package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // spring 에서 spring 컨테이너 생성 후 OrderServiceImpl객체를 등록 시 생성자를 호출한다. 결국 아래와 같이 호출하게 되며, 매개변수로 들어가는 memberRepository, discountPolicy도 spring 컨테이너에서 찾아와서 넣어준다.
    // new OrderServiceImpl(memberRepository, discountPolicy)
    // @Autowired // Autowired는 생성자가 하나면 생략 가능하다.
    // lombok의 RequriedArgsConstructor 사용 시 아래 생성자가 자동으로 만들어 진다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
