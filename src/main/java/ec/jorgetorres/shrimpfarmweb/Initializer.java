package ec.jorgetorres.shrimpfarmweb;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.repository.PondRepository;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import ec.jorgetorres.shrimpfarmweb.service.PondService;
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
  private final PondService pondService;

  @Autowired
  public Initializer(ShrimpFarmRepository shrimpFarmRepository, PondService pondService) {
    this.shrimpFarmRepository = shrimpFarmRepository;
    this.pondService = pondService;
  }

  @Override
  public void run(String... args) throws Exception {
    Stream.of("La Victoria", "The New Era", "James Blue", "Richmond Hills").forEach(name ->
        shrimpFarmRepository.save(new ShrimpFarm(name))
    );

    ShrimpFarm shrimpFarm = shrimpFarmRepository.findByName("La Victoria");
    Pond pond = Pond.builder().name("San Fernandino")
              .size(new BigDecimal(100.4))
              .idShrimpFarm(shrimpFarm.getId())
              .build();

    pondService.save(pond);
    shrimpFarmRepository.findAll().forEach(System.out::println);
  }
}