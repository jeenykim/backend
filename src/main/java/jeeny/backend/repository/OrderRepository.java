package jeeny.backend.repository;

import jeeny.backend.entity.Cart;
import jeeny.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByMemberIdOrderByIdDesc(int memberId);
    //memberId값을 인자로 받으면 member에 있는 카트정보를 List형태로 가져옴



}
