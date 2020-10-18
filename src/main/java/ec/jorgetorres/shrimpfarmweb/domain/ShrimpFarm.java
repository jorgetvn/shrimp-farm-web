package ec.jorgetorres.shrimpfarmweb.domain;

import lombok.*;

import javax.persistence.*;

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

  @Override
  public String toString() {
    return "ShrimpFarm [name=" + name + "]";
  }
}
