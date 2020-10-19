package ec.jorgetorres.shrimpfarmweb.service;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PondService {
  private PondRepository pondRepository;

  @Autowired
  public PondService(PondRepository pondRepository) {
    super();
    this.pondRepository = pondRepository;
  }

  public List<Pond> list() {
    return pondRepository.findAllByOrderByNameAsc();
  }

  public Optional<Pond> findById(Long id) {
    return pondRepository.findById(id);
  }

  public Pond save(Pond pond) {
    return pondRepository.save(pond);
  }

  public void deletePond(Pond pond) { pondRepository.delete(pond); }
}
