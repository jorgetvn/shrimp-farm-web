package ec.jorgetorres.shrimpfarmweb.service;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.repository.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
