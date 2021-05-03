package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByDeletedAt(LocalDate deletedAt);

    Optional<Customer> findByEmail(String email);
}
