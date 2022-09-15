package jeeny.backend.repository;

import jeeny.backend.entity.Cart;
import jeeny.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberId(int memberId);
    //memberId값을 인자로 받으면 member에 있는 카트정보를 List형태로 가져옴

    Cart findByMemberIdAndItemId(int memberId, int itemId);
    //memberId, itemId의 카트정보를 가져옴

    void deleteByMemberId(int memberId);
}
