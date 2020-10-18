package ec.jorgetorres.shrimpfarmweb.domain;

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
  @Column(name="idPond")
  private Long id;
  @NonNull
  private String name;
  private BigDecimal size;
}
