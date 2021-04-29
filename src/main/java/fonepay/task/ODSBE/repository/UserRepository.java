package fonepay.task.ODSBE.repository;

import fonepay.task.ODSBE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByDeletedAt(LocalDate deletedAt);

}
