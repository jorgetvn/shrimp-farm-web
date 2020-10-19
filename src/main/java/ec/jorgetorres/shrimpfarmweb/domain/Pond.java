package ec.jorgetorres.shrimpfarmweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="pond")
public class Pond {
  @Id
  @GeneratedValue
  @Column(name="id_pond")
  private Long id;
  @NonNull
  private String name;
  private BigDecimal size;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="idshrimpfarm")
  @JsonIgnore
  private ShrimpFarm shrimpFarm;
  @Transient
  private ShrimpFarm shrimpFarmAux;
}
