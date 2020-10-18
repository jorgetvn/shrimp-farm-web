package ec.jorgetorres.shrimpfarmweb;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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
    Stream.of("La Victoria", "The New Era", "James Blue", "Richmond Hills").forEach(name ->
        shrimpFarmRepository.save(new ShrimpFarm(name))
    );

    ShrimpFarm shrimpFarm = shrimpFarmRepository.findByName("La Victoria");
    Pond p = Pond.builder().name("San Fernandino")
              .size(new BigDecimal(100.4))
              .shrimpFarm(shrimpFarm)
              .build();
    shrimpFarm.setPonds(Arrays.asList(p));
    shrimpFarmRepository.save(shrimpFarm);

    shrimpFarmRepository.findAll().forEach(System.out::println);
  }
}