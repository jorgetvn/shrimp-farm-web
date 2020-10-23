package ec.jorgetorres.shrimpfarmweb.service;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.repository.PondRepository;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PondService {
  private PondRepository pondRepository;
  private ShrimpFarmRepository shrimpFarmRepository;

  @Autowired
  public PondService(PondRepository pondRepository, ShrimpFarmRepository shrimpFarmRepository) {
    super();
    this.pondRepository = pondRepository;
    this.shrimpFarmRepository = shrimpFarmRepository;
  }

  public List<Pond> list() {
    return pondRepository.findAllByOrderByCreatedDateTimeAsc();
  }

  public Optional<Pond> findById(Long id) {
    return pondRepository.findById(id);
  }

  public Pond save(Pond pond) {
    final Optional<ShrimpFarm> shrimpFarm = shrimpFarmRepository.findById(pond.getIdShrimpFarm());
    pond.setShrimpFarm(shrimpFarm.get());
    return pondRepository.save(pond);
  }

  public void deletePond(Pond pond) { pondRepository.delete(pond); }
}
