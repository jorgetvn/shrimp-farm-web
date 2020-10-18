package ec.jorgetorres.shrimpfarmweb.rest;

import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.service.PondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

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

  @GetMapping("/pond/{id}")
  ResponseEntity<?> getPond(@PathVariable Long id) {
    Optional<Pond> pond = pondService.findById(id);
    return pond.map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/pond")
  ResponseEntity<Pond> createPond(@Valid @RequestBody Pond shrimpFarm) throws URISyntaxException {
    Pond result = pondService.save(shrimpFarm);
    return ResponseEntity.created(new URI("/api/pond/" + result.getId()))
        .body(result);
  }

  @PutMapping("/pond/{id}")
  ResponseEntity<Pond> updatePond(@Valid @RequestBody Pond shrimpFarm) {
    Pond result = pondService.save(shrimpFarm);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("/pond/{id}")
  public ResponseEntity<?> deletePond(@PathVariable Long id) {
    pondService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
