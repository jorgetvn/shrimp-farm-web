package ec.jorgetorres.shrimpfarmweb;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.service.ShrimpFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

  private ShrimpFarmService shrimpFarmService;

  @Autowired
  public HomeController(ShrimpFarmService shrimpFarmService) {
    super();
    this.shrimpFarmService = shrimpFarmService;
  }

  @GetMapping("/shrimpfarms")
  public List<ShrimpFarm> home() {
    return shrimpFarmService.list();
  }
}
