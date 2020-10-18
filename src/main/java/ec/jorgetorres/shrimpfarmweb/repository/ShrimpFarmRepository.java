package ec.jorgetorres.shrimpfarmweb.repository;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShrimpFarmRepository extends JpaRepository<ShrimpFarm, Long> {
  List<ShrimpFarm> findAllByOrderByNameAsc();
  ShrimpFarm findByName(String name);
}
