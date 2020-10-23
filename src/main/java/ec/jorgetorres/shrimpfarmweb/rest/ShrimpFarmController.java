package ec.jorgetorres.shrimpfarmweb.rest;

import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.service.ShrimpFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShrimpFarmController {

  private ShrimpFarmService shrimpFarmService;

  @Autowired
  public ShrimpFarmController(ShrimpFarmService shrimpFarmService) {
    super();
    this.shrimpFarmService = shrimpFarmService;
  }

  @GetMapping("/shrimpfarms")
  Collection<ShrimpFarm> shrimpfarms() {
    return shrimpFarmService.list();
  }

  @GetMapping("/shrimpfarm/{id}")
  ResponseEntity<?> getShrimpFarm(@PathVariable Long id) {
    Optional<ShrimpFarm> shrimpFarm = shrimpFarmService.findById(id);
    return shrimpFarm.map(response -> ResponseEntity.ok().body(response))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/shrimpfarm")
  ResponseEntity<ShrimpFarm> createShrimpFarm(@Valid @RequestBody ShrimpFarm shrimpFarm) throws URISyntaxException {
    ShrimpFarm result = shrimpFarmService.save(shrimpFarm);
    return ResponseEntity.created(new URI("/api/shrimpfarm/" + result.getId()))
        .body(result);
  }

  @PutMapping("/shrimpfarm")
  ResponseEntity<ShrimpFarm> updateShrimpFarm(@Valid @RequestBody ShrimpFarm shrimpFarm) {
    ShrimpFarm result = shrimpFarmService.save(shrimpFarm);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/shrimpfarm/{id}")
  public ResponseEntity<?> deleteShrimpFarm(@PathVariable Long id) {
    shrimpFarmService.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
