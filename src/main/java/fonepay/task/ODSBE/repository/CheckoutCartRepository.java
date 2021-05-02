package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.model.CheckoutCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutCartRepository extends JpaRepository<CheckoutCart, Long> {

    List<CheckoutCart> findAllByCustomerId(long customerId);

}
