package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByDeletedAt(LocalDate deletedAt);
}
