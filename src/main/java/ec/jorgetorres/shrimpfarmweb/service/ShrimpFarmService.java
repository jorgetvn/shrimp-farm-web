package ec.jorgetorres.shrimpfarmweb.service;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.domain.SortDTO;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShrimpFarmService {
  private ShrimpFarmRepository shrimpFarmRepository;

  @Autowired
  public ShrimpFarmService(ShrimpFarmRepository shrimpFarmRepository) {
    super();
    this.shrimpFarmRepository = shrimpFarmRepository;
  }

  public List<ShrimpFarm> listSorted(List<SortDTO> orderingList) {
    List<Sort.Order> orders = new ArrayList<>();
    if (orderingList != null && !orderingList.isEmpty()) {
      orderingList.stream().forEach(sort -> {
        if (sort.getDirection() != null && sort.getDirection().equals("DESC")) {
          orders.add(new Sort.Order(Sort.Direction.DESC, sort.getProp()));
        } else if (sort.getDirection() != null && sort.getDirection().equals("ASC")) {
          orders.add(new Sort.Order(Sort.Direction.ASC, sort.getProp()));
        }
      });
      return shrimpFarmRepository.findAll(Sort.by(orders));
    }
    return shrimpFarmRepository.findAllByOrderByCreatedDateTimeAsc();
  }

  public List<ShrimpFarm> list(){
    return shrimpFarmRepository.findAllByOrderByCreatedDateTimeAsc();
  }

  public Optional<ShrimpFarm> findById(Long id) {
    return shrimpFarmRepository.findById(id);
  }

  public ShrimpFarm save(ShrimpFarm shrimpFarm) {
    return shrimpFarmRepository.save(shrimpFarm);
  }

  public void deleteById(Long id) {
    shrimpFarmRepository.deleteById(id);
  }
}
