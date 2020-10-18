package ec.jorgetorres.shrimpfarmweb.repository;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PondRepository extends JpaRepository<Pond, Long> {
  List<Pond> findAllByOrderByNameAsc();
  Pond findByName(String name);
}
