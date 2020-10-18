package ec.jorgetorres.shrimpfarmweb;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {
  private final ShrimpFarmRepository shrimpFarmRepository;

  @Autowired
  public Initializer(ShrimpFarmRepository shrimpFarmRepository) {
    this.shrimpFarmRepository = shrimpFarmRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Stream.of("La Victoria", "The New Era", "James Blue", "Richmond JUG").forEach(name ->
        shrimpFarmRepository.save(new ShrimpFarm(name))
    );

    shrimpFarmRepository.findAll().forEach(System.out::println);
  }
}