package ec.jorgetorres.shrimpfarmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShrimpFarmWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShrimpFarmWebApplication.class, args);
  }

}
