package ec.jorgetorres.shrimpfarmweb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
  @OneToMany(fetch = FetchType.EAGER, mappedBy="shrimpFarm", cascade=CascadeType.ALL, orphanRemoval = true)
  private List<Pond> ponds;


  @Override
  public String toString() {
    return "ShrimpFarm [name=" + name + "]";
  }
}
