package ec.jorgetorres.shrimpfarmweb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="shrimp_farm")
public class ShrimpFarm {
  @Id
  @GeneratedValue
  @Column(name="idshrimpfarm")
  private Long id;
  @NonNull
  private String name;
  @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
  private Set<Pond> ponds;

  @Override
  public String toString() {
    return "ShrimpFarm [name=" + name + "]";
  }
}
