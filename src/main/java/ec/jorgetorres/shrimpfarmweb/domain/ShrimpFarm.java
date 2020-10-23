package ec.jorgetorres.shrimpfarmweb.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "shrimp_farm")
public class ShrimpFarm {
  @Id
  @GeneratedValue
  @Column(name = "idshrimpfarm")
  private Long id;
  @NonNull
  @Column(unique = true)
  private String name;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "shrimpFarm", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pond> ponds;
  @LastModifiedDate
  private LocalDateTime lastModifiedDateTime;
  @CreatedDate
  private LocalDateTime createdDateTime;

  @Override
  public String toString() {
    return "ShrimpFarm [name=" + name + "]";
  }
}
