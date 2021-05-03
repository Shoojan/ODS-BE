package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderToCartRepository extends JpaRepository<Order, Long> {

    @Query("Select o FROM orders o WHERE o.deletedAt=null AND o.customerId=:customerId AND o.orderStatus=:orderStatus")
    List<Order> fetchActiveOrders(@Param("customerId") long customerId,
                                  @Param("orderStatus") OrderStatus orderStatus);

    @Query("Select o FROM orders o WHERE o.deletedAt<>null AND o.customerId=:customerId AND o.orderStatus=:orderStatus")
    List<Order> fetchDeletedOrders(@Param("customerId") long customerId,
                                   @Param("orderStatus") OrderStatus orderStatus);

}
