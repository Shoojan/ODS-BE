package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderToCartRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerIdAndOrderStatus(long customerId, OrderStatus orderStatus);

}
