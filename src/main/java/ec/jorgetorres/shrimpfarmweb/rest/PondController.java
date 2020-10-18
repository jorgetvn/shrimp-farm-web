package ec.jorgetorres.shrimpfarmweb.rest;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.service.PondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PondController {

  private PondService pondService;

  @Autowired
  public PondController(PondService pondService) {
    super();
    this.pondService = pondService;
  }

  @GetMapping("/ponds")
  Collection<Pond> ponds() {
    return pondService.list();
  }
}
