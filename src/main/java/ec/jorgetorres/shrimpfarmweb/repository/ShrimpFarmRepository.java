package ec.jorgetorres.shrimpfarmweb.repository;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShrimpFarmRepository extends CrudRepository<ShrimpFarm, Long> {
  List<ShrimpFarm> findAllByOrderByNameAsc();
}
