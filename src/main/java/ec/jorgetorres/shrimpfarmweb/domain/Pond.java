package ec.jorgetorres.shrimpfarmweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="pond")
public class Pond {
  @Id
  @GeneratedValue
  @Column(name="id_pond")
  private Long id;
  @NotNull(message = "Name can't be null")
  @NonNull
  @Column(unique = true)
  private String name;
  @NotNull(message = "Size can't be null")
  @NonNull
  @DecimalMin(value = "1", inclusive = true)
  private BigDecimal size;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="idshrimpfarm")
  @JsonIgnore
  private ShrimpFarm shrimpFarm;
  @Transient
  private ShrimpFarm shrimpFarmAux;
  @LastModifiedDate
  private LocalDateTime lastModifiedDateTime;
  @CreatedDate
  private LocalDateTime createdDateTime;
}
